package com.pfe.Back.service.impl;

import java.time.Instant ;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import java.util.stream.Collectors;
import sendgrid.EmailRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.pfe.Back.model.Formations;
import com.pfe.Back.model.Operateurs;
import com.pfe.Back.model.Evaluation;
import com.pfe.Back.model.User;
import com.pfe.Back.model.UserLigneAssignement;
import com.pfe.Back.model.DemandeDeRecrutement;
import com.pfe.Back.model.EtatDemandeDeFormation;
import com.pfe.Back.repository.operationRepository;
import com.pfe.Back.model.Operation;
import com.pfe.Back.model.Roles;
import com.pfe.Back.DTO.DemandeDeRecrutementDTO;
import com.pfe.Back.DTO.OperateurDTO;
import com.pfe.Back.repository.DemandeDeRecrutementRepository;
import com.pfe.Back.repository.EvaluationRepository;
import com.pfe.Back.repository.FormationsRepository;
import com.pfe.Back.repository.OperateurRepository;
import com.pfe.Back.service.DemandeDeRecrutementService;
import com.pfe.Back.service.EmailService;
import com.sendgrid.Response;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class DemandeDeRecrutementServiceImpl implements DemandeDeRecrutementService {

	@Autowired
    private EmailService emailService;
	@Autowired
	private DemandeDeRecrutementRepository demandeRepository ;
	@Autowired
	private OperateurRepository operateurRepository ;
	@Autowired
	private operationRepository operationRepository ;
	@Autowired
	private FormationsRepository formationRepository ;
	@Autowired
	private EvaluationRepository evaluationRepository ;
	@Override
	public DemandeDeRecrutementDTO save(DemandeDeRecrutementDTO dto) {
	    if (dto.getEtat() == EtatDemandeDeFormation.VALIDER_PAR_DIRECTEUR_OPERATIONS) {
	        Formations formation = new Formations();
	        formation.setMatriculeFormation("F" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
	        formation.setTypeDeFormation("Besoin de personnel");
	        formationRepository.save(formation);
	        dto.setIdFormations(formation.getId());
	    }

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String loggedInUser = authentication.getName();
	    dto.setLastModifiedBy(loggedInUser);
	    
	   // User connectedUser = (User) authentication.getPrincipal();
	    Optional<Operation> operation = operationRepository.findById(dto.getIdOperation());
	    

	    Integer idLigne = operation.get().getLigne().getId();
	    List<UserLigneAssignement> assignments = operation.get().getLigne().getUserLigneAssignement();
	    
	    // Debugging logs
	    log.info("Operation: {}", operation.get());
	    log.info("idLigne: {}", idLigne);
	    log.info("Assignments: {}", assignments);

	    List<String> usernamesForLigne = assignments.stream()
	            .filter(assignment -> idLigne.equals(assignment.getIdLigne())&& assignment.getUser().getRole() == Roles.ResponssableUAP)
	            .map(assignment -> assignment.getUser().getUsername())
	            .collect(Collectors.toList());

	    log.info("Sending emails to: {}", usernamesForLigne);

	    for (String email : usernamesForLigne) {
	        EmailRequest emailRequest = new EmailRequest();
	        emailRequest.setTo(email);
	        emailRequest.setSubject("Création d'une demande");
	        emailRequest.setBody("Une demande de recrutement est crée par :" + loggedInUser);

	        Response emailResponse = emailService.sendEmail(emailRequest);
	        /*if (emailResponse.getStatusCode() != 202) {
	            log.error("Failed to send email notification to {}: Status code {}", email, emailResponse.getStatusCode());
	        } else {
	            log.info("Email sent successfully to {}", email);
	        }*/
	    }
        dto.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


	    return DemandeDeRecrutementDTO.fromEntity(demandeRepository.save(DemandeDeRecrutementDTO.toEntity(dto)));
	}
	@Override
	public List<DemandeDeRecrutementDTO> findAll() {
	
		// TODO Auto-generated method stub
		return demandeRepository.findAll().stream().map(DemandeDeRecrutementDTO::fromEntity).collect(Collectors.toList());
	}
	@Override
	public DemandeDeRecrutementDTO updateEtatDemande(Integer idDemande, EtatDemandeDeFormation etatDemande) {
	    Optional<DemandeDeRecrutement> demandeOptional = demandeRepository.findById(idDemande);

	    if (!demandeOptional.isPresent()) {
	        throw new IllegalArgumentException("Demande de recrutement non trouvée avec l'ID : " + idDemande);
	    }

	    DemandeDeRecrutement demande = demandeOptional.get();
	    EtatDemandeDeFormation etatCourant = demande.getEtat();
	    boolean etatChanged = false;

	    Optional<Operation> operationOptional = operationRepository.findById(demande.getPosteAPouvoir().getId());
	    if (!operationOptional.isPresent()) {
	        throw new IllegalArgumentException("Operation non trouvée pour la demande ID : " + idDemande);
	    }

	    Operation operation = operationOptional.get();
	    Integer idLigne = operation.getLigne().getId();
	    List<UserLigneAssignement> assignments = operation.getLigne().getUserLigneAssignement();

	    log.info("Operation: {}", operation);
	    log.info("idLigne: {}", idLigne);
	    log.info("Assignments: {}", assignments);

	    List<String> usernamesForLigne = getUsernamesForLigne(assignments, idLigne, etatDemande);
	    log.info("Sending emails to: {}", usernamesForLigne);

	    if (etatDemande != etatCourant && etatCourant != EtatDemandeDeFormation.VALIDER_PAR_DIRECTEUR_OPERATIONS) {
	        demande.setEtat(etatDemande);
	        etatChanged = true;
	    }

	    if (etatCourant != EtatDemandeDeFormation.VALIDER_PAR_DIRECTEUR_OPERATIONS 
	        && etatDemande == EtatDemandeDeFormation.VALIDER_PAR_DIRECTEUR_OPERATIONS 
	        && demande.getFormation() == null) {

	        Formations formation = new Formations();
	        formation.setMatriculeFormation("F" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
	        formation.setTypeDeFormation("Besoin de personnel");
	        //formation.setDemandeFormation(demande);
	        formationRepository.save(formation);
	        demande.setFormation(formation);
	    }

	    if (etatChanged) {
	        sendEmailNotifications(usernamesForLigne, idDemande, etatDemande);
	    }

	    return DemandeDeRecrutementDTO.fromEntity(demandeRepository.save(demande));
	}

	private List<String> getUsernamesForLigne(List<UserLigneAssignement> assignments, Integer idLigne, EtatDemandeDeFormation etatDemande) {
	    return assignments.stream()
	        .filter(assignment -> idLigne.equals(assignment.getIdLigne()) && isMatchingRole(assignment.getUser().getRole(), etatDemande))
	        .map(assignment -> assignment.getUser().getUsername())
	        .collect(Collectors.toList());
	}

	private boolean isMatchingRole(Roles role, EtatDemandeDeFormation etatDemande) {
	    switch (etatDemande) {
	        case Envoyé_Vers_RUAP:
	            return role == Roles.ResponssableUAP;
	        case VALIDER_PAR_RESPONSABLE_UAP:
	        case Refuser_PAR_RESPONSABLE_UAP:
	            return role == Roles.DirecteurUAP;
	        case VALIDER_PAR_DIRECTEUR_UAP:
	        case Refuser_PAR_DIRECTEUR_UAP:
	            return role == Roles.DirecteurDesOperations;
	        case VALIDER_PAR_DIRECTEUR_OPERATIONS :
	        case  Refuser_PAR_DIRECTEUR_OPERATIONS:
	        	return role == Roles.RH ;
	        default:
	            return false;
	    }
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
	        /*if (emailResponse.getStatusCode() != 202) {
	            log.error("Failed to send email notification for demande ID: " + idDemande);
	        }*/
	    }
	}

	@Override
	public DemandeDeRecrutementDTO updateListOperateur(Integer idDemande, List<OperateurDTO> listOperateur) {
	    Optional<DemandeDeRecrutement> demande = demandeRepository.findById(idDemande);
	    DemandeDeRecrutement demande1 = demande.get();
	    demande1.setNombre(demande1.getNombre() - ((CharSequence) listOperateur).length());
	    List<Operateurs> listOperateurs = new ArrayList<Operateurs>();
	    for(OperateurDTO operateurDTO : listOperateur) {
	        Operateurs operateur = OperateurDTO.toEntity(operateurDTO);
	        operateur.setDemande(demande1); // Set idDemande from method parameter
	        operateurRepository.save(operateur); // Save operateur with correct idDemande
	        listOperateurs.add(operateur);
	    }
	    demande1.setOperateurs(listOperateurs);
	    return DemandeDeRecrutementDTO.fromEntity(demandeRepository.save(demande1));
	}
	@Override
	public DemandeDeRecrutementDTO findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.of(DemandeDeRecrutementDTO.fromEntity(demandeRepository.findById(id).get())).orElseThrow();
	}
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		demandeRepository.deleteById(id);
		
	}
	@Override
	public DemandeDeRecrutementDTO updateDemande(Integer idDemande, DemandeDeRecrutementDTO Dto) {
		if(idDemande == null) {
			log.error("Demande ID is Null");

		}
	    Optional<DemandeDeRecrutement> demande = demandeRepository.findById(idDemande);
	    
		//Optional<DemandeDeRecrutement> articleOptional =demandeRepository.findById(idDemande);
    	
    	
    	
    	return DemandeDeRecrutementDTO.fromEntity(demandeRepository.save(DemandeDeRecrutementDTO.toEntity(Dto)));
	}
	@Override
	public DemandeDeRecrutementDTO justsave(DemandeDeRecrutementDTO dto) {
		// TODO Auto-generated method stub
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String loggedInUser = authentication.getName();
		    dto.setLastModifiedBy(loggedInUser);
	    return DemandeDeRecrutementDTO.fromEntity(demandeRepository.save(DemandeDeRecrutementDTO.toEntity(dto)));
	}
	
	
	public DemandeDeRecrutementDTO update(Integer demandeId) {
	    // Récupérer la demande existante à partir du repository
	    Optional<DemandeDeRecrutement> optionalDemande = demandeRepository.findById(demandeId);
	    if (!optionalDemande.isPresent()) {
	        throw new EntityNotFoundException("Demande not found with id " + demandeId);
	    }
	    
	    DemandeDeRecrutement demande = optionalDemande.get();
	    DemandeDeRecrutementDTO dto = DemandeDeRecrutementDTO.fromEntity(demande);

	    // Vérifier l'état de la demande
	    if (dto.getEtat() == EtatDemandeDeFormation.VALIDER_PAR_DIRECTEUR_OPERATIONS) {
	        Formations formation = new Formations();
	        formation.setMatriculeFormation("F" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
	        formation.setTypeDeFormation("Besoin de personnel");
	        formationRepository.save(formation);
	        dto.setIdFormations(formation.getId());
	    }

	    // Obtenir l'utilisateur connecté
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String loggedInUser = authentication.getName();
	    dto.setLastModifiedBy(loggedInUser);
	    dto.setEtat(EtatDemandeDeFormation.Envoyé_Vers_RUAP);
	    
	    // Récupérer l'opération associée
	    Optional<Operation> optionalOperation = operationRepository.findById(dto.getIdOperation());
	    if (!optionalOperation.isPresent()) {
	        throw new EntityNotFoundException("Operation not found with id " + dto.getIdOperation());
	    }

	    Operation operation = optionalOperation.get();
	    Integer idLigne = operation.getLigne().getId();
	    List<UserLigneAssignement> assignments = operation.getLigne().getUserLigneAssignement();
	    
	    // Logs pour le debugging
	    log.info("Operation: {}", operation);
	    log.info("idLigne: {}", idLigne);
	    log.info("Assignments: {}", assignments);

	    // Filtrer et collecter les emails des responsables UAP
	    List<String> usernamesForLigne = assignments.stream()
	            .filter(assignment -> idLigne.equals(assignment.getIdLigne()) && assignment.getUser().getRole() == Roles.ResponssableUAP)
	            .map(assignment -> assignment.getUser().getUsername())
	            .collect(Collectors.toList());

	    log.info("Sending emails to: {}", usernamesForLigne);

	    // Envoyer des notifications par e-mail
	    for (String email : usernamesForLigne) {
	        EmailRequest emailRequest = new EmailRequest();
	        emailRequest.setTo(email);
	        emailRequest.setSubject("Création d'une demande");
	        emailRequest.setBody("Une demande de recrutement est créée par : " + loggedInUser);

	        Response emailResponse = emailService.sendEmail(emailRequest);
	       /* if (emailResponse.getStatusCode() != 202) {
	            log.error("Failed to send email notification to {}: Status code {}", email, emailResponse.getStatusCode());
	        } else {
	            log.info("Email sent successfully to {}", email);
	        }*/
	    }

	    // Sauvegarder les modifications et retourner le DTO mis à jour
	    DemandeDeRecrutement updatedDemande = demandeRepository.save(DemandeDeRecrutementDTO.toEntity(dto));
	    return DemandeDeRecrutementDTO.fromEntity(updatedDemande);
	}


	

}
