package com.pfe.Back.service.impl;

import java.time.Instant; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pfe.Back.DTO.DemandeDePolyvalenceDTO;
import com.pfe.Back.DTO.DemandeDePolyvalenceDataDTO;
import com.pfe.Back.DTO.DemandeDeRecrutementDTO;
import com.pfe.Back.model.DemandeDePolyvalence;
import com.pfe.Back.model.DemandeDePolyvalenceData;
import com.pfe.Back.model.DemandeDeRecrutement;
import com.pfe.Back.model.EtatDemandeDeFormation;
import com.pfe.Back.model.Formations;
import com.pfe.Back.model.Ligne;
import com.pfe.Back.model.Roles;
import com.pfe.Back.model.UserLigneAssignement;
import com.pfe.Back.model.Evaluation;
import com.pfe.Back.repository.ligneRepository;
import com.pfe.Back.model.OperateurOperationAssignment;

import com.pfe.Back.repository.DemandeDePolyvalenceDataRepository;
import com.pfe.Back.repository.DemandeDePolyvalenceRepository;
import com.pfe.Back.repository.EvaluationRepository;
import com.pfe.Back.repository.FormationsRepository;
import com.pfe.Back.service.DemandeDePolyvalenceService;
import com.pfe.Back.service.EmailService;
import com.sendgrid.Response;
import com.pfe.Back.repository.OperateurOperationAssignmentRepository;

import lombok.extern.slf4j.Slf4j;
import sendgrid.EmailRequest;

@Service
@Slf4j
public class DemandeDePolyvalenceServiceImpl implements DemandeDePolyvalenceService{
	
	
	@Autowired
	private DemandeDePolyvalenceRepository demandeRepository ;
	@Autowired
	private OperateurOperationAssignmentRepository ooar ;

	@Autowired
    private EmailService emailService;
	@Autowired
	private ligneRepository ligneRepository ;
	@Autowired
	private EvaluationRepository evaluationRepository ;
	@Autowired
	private DemandeDePolyvalenceDataRepository demandeDataRepository ;
	@Autowired
	private FormationsRepository formationRepository ;
	@Override
	public DemandeDePolyvalenceDTO save(DemandeDePolyvalenceDTO dto) {
        if (dto.getEtat() == EtatDemandeDeFormation.VALIDER_PAR_RESPONSABLE_UAP) {
            Formations formation = new Formations();
            formation.setMatriculeFormation("F" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
            formation.setTypeDeFormation("Polyvalence");
            formationRepository.save(formation);
            dto.setIdFormations(formation.getId());
        }

        Integer idNouvelleLigne = dto.getDemandDataList().get(0).getIdNouvelleLigne();
        Optional<Ligne> ligneOptional = ligneRepository.findById(idNouvelleLigne);
        /*if (ligneOptional.isEmpty()) {
            throw new LigneNotFoundException("Ligne not found with ID: " + idNouvelleLigne);
        }
*/
        Ligne ligne = ligneOptional.get();
        List<UserLigneAssignement> assignments = ligne.getUserLigneAssignement();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();
        dto.setLastModifiedBy(loggedInUser);

        List<String> usernamesForLigneAndRUAP = assignments.stream()
                .filter(assignment -> idNouvelleLigne.equals(assignment.getIdLigne()) &&
                        assignment.getUser().getRole() == Roles.ResponssableUAP)
                .map(assignment -> assignment.getUser().getEmail())
                .collect(Collectors.toList());

        log.info("Sending emails to: {}", usernamesForLigneAndRUAP);

        for (String email : usernamesForLigneAndRUAP) {
            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setTo(email);
            emailRequest.setSubject("Etat de la demande mis à jour");
            emailRequest.setBody("Une demande de polyvalence est créée par : " + loggedInUser);

            try {
                Response emailResponse = emailService.sendEmail(emailRequest);
                if (emailResponse.getStatusCode() != 202) {
                    log.error("Failed to send email notification to {}: Status code {}", email, emailResponse.getStatusCode());
                } else {
                    log.info("Email sent successfully to {}", email);
                }
            } catch (Exception e) {
                log.error("Error sending email to {}: {}", email, e.getMessage());
            }
        }

        DemandeDePolyvalence demande = demandeRepository.save(DemandeDePolyvalenceDTO.toEntity(dto));

        for (DemandeDePolyvalenceDataDTO demandeData : dto.getDemandDataList()) {
            demandeData.setIdDemandeDePolyvalence(demande.getId());
            demandeDataRepository.save(DemandeDePolyvalenceDataDTO.toEntity(demandeData));
        }
        demande.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return DemandeDePolyvalenceDTO.fromEntity(demande);
    }
	
	@Override
	public List<DemandeDePolyvalenceDTO> findAll() {
		// TODO Auto-generated method stub
		return demandeRepository.findAll().stream().map(DemandeDePolyvalenceDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public DemandeDePolyvalenceDataDTO save(DemandeDePolyvalenceDataDTO dto) {
		// TODO Auto-generated method stub
		return DemandeDePolyvalenceDataDTO.fromEntity(demandeDataRepository.save(DemandeDePolyvalenceDataDTO.toEntity(dto)));
	}
	@Override
	public DemandeDePolyvalenceDTO updateEtatDemande(Integer idDemande, EtatDemandeDeFormation etatDemande) {
		// TODO Auto-generated method stub
		Optional<DemandeDePolyvalence> demande = demandeRepository.findById(idDemande);
		DemandeDePolyvalence demande1=demande.get();
	    boolean etatChanged = false;
		demande1.setEtat(etatDemande);
		etatChanged = true;
		Evaluation evaluation = new Evaluation();
		OperateurOperationAssignment ooa = new OperateurOperationAssignment();
		if(demande1.getEtat()== EtatDemandeDeFormation.VALIDER_PAR_RESPONSABLE_UAP && demande1.getFormationPoly() == null) {
			
			Formations formation = new Formations();
			formation.setMatriculeFormation("F" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
			formation.setTypeDeFormation("Polyvalence");
			formationRepository.save(formation);
			demande1.setFormationPoly(formation);
			for(DemandeDePolyvalenceData demandeData : demande1.getDemandDataList()) {
			evaluation.setOperation(demandeData.getNouvelleOperation());
			evaluation.setOperateurs(demandeData.getOperateurs());
			evaluation.setFormation(formation);
			 evaluation.setEvaluationTotal("0");
             evaluation.setEvaluationParAgentFormation("0");
             evaluation.setEvaluationParChefDeLigne("0");
             evaluation.setEvaluationParIngénieurQualité("0");
             evaluation.setEvaluationParMaintenance("0");
			evaluationRepository.save(evaluation);
			ooa.setOperateurs(demandeData.getOperateurs());
			ooa.setOperation(demandeData.getNouvelleOperation());
			ooar.save(ooa);
			}
			
	        
			 Integer idNouvelleLigne = demande1.getDemandDataList().get(0).getNouvelleLigne().getId();
		     Optional<Ligne> ligneOptional = ligneRepository.findById(idNouvelleLigne);
		     Ligne ligne = ligneOptional.get();
		     List<UserLigneAssignement> assignments = ligne.getUserLigneAssignement();
		     List<String> usernamesForLigneAndRH = assignments.stream()
		                .filter(assignment -> idNouvelleLigne.equals(assignment.getIdLigne()) &&
		                        assignment.getUser().getRole() == Roles.RH)
		                .map(assignment -> assignment.getUser().getEmail())
		                .collect(Collectors.toList());
		     if (etatChanged) {
			        sendEmailNotifications(usernamesForLigneAndRH, idDemande, etatDemande);
			    }
			}
		return DemandeDePolyvalenceDTO.fromEntity(demandeRepository.save(demande1));
	}
	

	private void sendEmailNotifications(List<String> emails, Integer idDemande, EtatDemandeDeFormation etatDemande) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();
		for (String email : emails) {
	        EmailRequest emailRequest = new EmailRequest();
	        emailRequest.setTo(email);
	        emailRequest.setSubject("Etat de la demande mis à jour");
	        emailRequest.setBody("L'état de la demande avec l'ID " + idDemande + " a été mis à jour à " + etatDemande + " par " + loggedInUser);

	        Response emailResponse = emailService.sendEmail(emailRequest);
	        if (emailResponse.getStatusCode() != 202) {
	            log.error("Failed to send email notification for demande ID: " + idDemande);
	        }
	    }
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Optional<DemandeDePolyvalence> demande = demandeRepository.findById(id);
		DemandeDePolyvalence demande1=demande.get();
		for(DemandeDePolyvalenceData demandeData : demande1.getDemandDataList()) {
			demandeDataRepository.deleteById(demandeData.getId());
		}
			demandeRepository.deleteById(id);
	}

	@Override
	public DemandeDePolyvalenceDTO findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.of(DemandeDePolyvalenceDTO.fromEntity(demandeRepository.findById(id).get())).orElseThrow();
	}

	@Override
	public DemandeDePolyvalenceDTO justsave(DemandeDePolyvalenceDTO dto) {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUser = authentication.getName();
        dto.setLastModifiedBy(loggedInUser);
		return DemandeDePolyvalenceDTO.fromEntity(demandeRepository.save(DemandeDePolyvalenceDTO.toEntity(dto)));
	}
	
	
	
	public DemandeDePolyvalenceDTO update(Integer demandeId) {
	    // Rechercher la demande existante par ID
	    Optional<DemandeDePolyvalence> existingDemandeOptional = demandeRepository.findById(demandeId);
	    
	    DemandeDePolyvalence existingDemande = existingDemandeOptional.get();
	    DemandeDePolyvalenceDTO dto = DemandeDePolyvalenceDTO.fromEntity(existingDemande);

	    if (dto.getEtat() == EtatDemandeDeFormation.VALIDER_PAR_RESPONSABLE_UAP) {
	        Formations formation = new Formations();
	        formation.setMatriculeFormation("F" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
	        formation.setTypeDeFormation("Polyvalence");
	        formationRepository.save(formation);
	        dto.setIdFormations(formation.getId());
	        existingDemande.setFormationPoly(formation);
	    }

	    Integer idNouvelleLigne = dto.getDemandDataList().get(0).getIdNouvelleLigne();
	    Optional<Ligne> ligneOptional = ligneRepository.findById(idNouvelleLigne);
	    

	    Ligne ligne = ligneOptional.get();
	    List<UserLigneAssignement> assignments = ligne.getUserLigneAssignement();

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String loggedInUser = authentication.getName();
	    existingDemande.setLastModifiedBy(loggedInUser);

	    List<String> usernamesForLigneAndRUAP = assignments.stream()
	            .filter(assignment -> idNouvelleLigne.equals(assignment.getIdLigne()) &&
	                    assignment.getUser().getRole() == Roles.ResponssableUAP)
	            .map(assignment -> assignment.getUser().getEmail())
	            .collect(Collectors.toList());

	    log.info("Sending emails to: {}", usernamesForLigneAndRUAP);

	    for (String email : usernamesForLigneAndRUAP) {
	        EmailRequest emailRequest = new EmailRequest();
	        emailRequest.setTo(email);
	        emailRequest.setSubject("Etat de la demande mis à jour");
	        emailRequest.setBody("Une demande de polyvalence est mise à jour par : " + loggedInUser);

	        try {
	            Response emailResponse = emailService.sendEmail(emailRequest);
	            if (emailResponse.getStatusCode() != 202) {
	                log.error("Failed to send email notification to {}: Status code {}", email, emailResponse.getStatusCode());
	            } else {
	                log.info("Email sent successfully to {}", email);
	            }
	        } catch (Exception e) {
	            log.error("Error sending email to {}: {}", email, e.getMessage());
	        }
	    }

	    DemandeDePolyvalence updatedDemande = demandeRepository.save(existingDemande);

	    for (DemandeDePolyvalenceDataDTO demandeData : dto.getDemandDataList()) {
	        demandeData.setIdDemandeDePolyvalence(updatedDemande.getId());
	        demandeDataRepository.save(DemandeDePolyvalenceDataDTO.toEntity(demandeData));
	    }

	    return DemandeDePolyvalenceDTO.fromEntity(updatedDemande);
	}

	
	
	
	

}
