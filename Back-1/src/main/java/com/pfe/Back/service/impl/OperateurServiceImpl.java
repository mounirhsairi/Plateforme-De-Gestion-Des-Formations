package com.pfe.Back.service.impl;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import java.util.ArrayList;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pfe.Back.model.Evaluation;
import com.pfe.Back.model.DemandeDePolyvalenceData;
import com.pfe.Back.model.DemandeDeRecrutement;
import com.pfe.Back.model.EtatDemandeDeFormation;
import com.pfe.Back.model.FIF;

import com.pfe.Back.DTO.OperateurDTO;
import com.pfe.Back.DTO.OperateurOperationAssignmentDTO;
import com.pfe.Back.model.Formations;
import com.pfe.Back.model.Ligne;
import com.pfe.Back.model.OperateurFromExcel;
import com.pfe.Back.model.OperateurOperationAssignment;
import com.pfe.Back.model.Operateurs;
import com.pfe.Back.model.Operation;
import com.pfe.Back.model.Qualification;
import com.pfe.Back.repository.ligneRepository;

import com.pfe.Back.repository.OperateurRepository;
import com.pfe.Back.repository.operationRepository;
import com.pfe.Back.repository.DemandeDePolyvalenceDataRepository;
import com.pfe.Back.repository.DemandeDeRecrutementRepository;
import com.pfe.Back.repository.EvaluationRepository;
import com.pfe.Back.repository.FIFRepository;
import com.pfe.Back.repository.FormationsRepository;
import com.pfe.Back.repository.OperateurOperationAssignmentRepository;


import com.pfe.Back.service.OperateurService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public final class OperateurServiceImpl implements OperateurService {

	private final OperateurRepository operateurRepository;
	private final operationRepository operationRepository ;
	private final OperateurOperationAssignmentRepository ooar;
	private final ligneRepository ligneRepository ;
	private final FormationsRepository formationRepository ;
	private final FIFRepository fifRepository ;
	private final EvaluationRepository evaluationRepository ;
	private final DemandeDeRecrutementRepository demandeRecrutementRepository ;
	private final DemandeDePolyvalenceDataRepository demandeDePolyvalenceDataRepository ;
    @Autowired
    public OperateurServiceImpl(OperateurRepository operateurRepository, operationRepository operationRepository, OperateurOperationAssignmentRepository ooar, ligneRepository ligneRepository, FormationsRepository formationRepository, FIFRepository fifRepository, DemandeDeRecrutementRepository demandeRecrutementRepository, EvaluationRepository evaluationRepository, DemandeDePolyvalenceDataRepository demandeDePolyvalenceDataRepository) {
        this.operateurRepository = operateurRepository;
		this.operationRepository = operationRepository;
		this.ooar = ooar;
		this.ligneRepository = ligneRepository;
		this.formationRepository = formationRepository;
		this.fifRepository = fifRepository;
		this.evaluationRepository = evaluationRepository;
		this.demandeRecrutementRepository = demandeRecrutementRepository;
		this.demandeDePolyvalenceDataRepository = demandeDePolyvalenceDataRepository;
    }

	@Override
	public OperateurDTO save(OperateurDTO dto, Integer idOperation) {
		// TODO Auto-generated method stub
		Optional<Operation> operationOptional = operationRepository.findById(idOperation);
        Operateurs operateur = operateurRepository.save(OperateurDTO.toEntity(dto));
		if (operationOptional.isPresent()) {
			 OperateurOperationAssignment ooa = new OperateurOperationAssignment();
		        ooa.setOperateurs(operateur);
		        ooa.setOperation(operationOptional.get());
		        ooa.setStartDate(Instant.now());
		        ooar.save(ooa);
	        }
		
		return OperateurDTO.fromEntity(operateur);
	}

	@Override
	public OperateurDTO findById(Integer id) {
		// TODO Auto-generated method stub
		if(id == null) {
			log.error("operateur id is null");
		}
		Optional<Operateurs> operateur =operateurRepository.findById(id);
		return Optional.of(OperateurDTO.fromEntity(operateur.get())).orElseThrow(); 
	}

	@Override
	public OperateurDTO findByMatriculeOperateur(String matricule) {
		// TODO Auto-generated method stub
		if(matricule == null) {
			log.error("operateur matricule is null");
		}
		Optional<Operateurs> operateur =operateurRepository.findByMatricule(matricule);
		return Optional.of(OperateurDTO.fromEntity(operateur.get())).orElseThrow(); 
	}

	@Override
	public List<OperateurDTO> findAll() {
		// TODO Auto-generated method stub
		return operateurRepository.findAll().stream().map(OperateurDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id ) {
		// TODO Auto-generated method stub
		if(id == null) {
			log.error("operateur id is null");
		}
		Operateurs operateur = operateurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Operateur not found with id: " + id));
        // Delete associated assignments
        for (OperateurOperationAssignment assignment : operateur.getAssignments()) {
        	ooar.delete(assignment);
        }
        List<Evaluation> evalutions =operateur.getEvalution();
        for(Evaluation evaluation :evalutions) {
        	evaluationRepository.delete(evaluation);
        }
        List<FIF> fifs =operateur.getPdfFiles();
        for(FIF fif :fifs) {
        	fifRepository.delete(fif);
        }
        List<DemandeDePolyvalenceData> demandesData =operateur.getData();
        for(DemandeDePolyvalenceData demandeData :demandesData) {
        	demandeDePolyvalenceDataRepository.delete(demandeData);
        }
		ooar.deleteById(id);/*je dois corriger */
		operateurRepository.deleteById(id);
	}

	@Override
	public List<OperateurDTO> findAllByChaine(String Chaine) {
		// TODO Auto-generated method stub
		return operateurRepository.findAllByChaine(Chaine).stream().map(OperateurDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<OperateurOperationAssignmentDTO> findAll1() {
		// TODO Auto-generated method stub
		return ooar.findAll().stream().map(OperateurOperationAssignmentDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<OperateurDTO> findOperatorsByIdLigne(Integer idLigne) {
	    Optional<Ligne> ligneOptional = ligneRepository.findById(idLigne);
	    List<OperateurDTO> operateurDTOs = new ArrayList<>();
	    List<Formations> formations = formationRepository.findAll();

	    if (ligneOptional.isPresent()) {
	        Ligne ligne = ligneOptional.get();
	        List<Operation> operations = ligne.getOperations();

	        Set<Integer> operatorIds = new HashSet<>(); // Set to store unique operator IDs

	        for (Operation operation : operations) {
	            List<OperateurOperationAssignment> assignments = operation.getAssignments();

	            for (OperateurOperationAssignment assignment : assignments) {
	                Operateurs operateur = assignment.getOperateurs();
	                operateur.setAssignments(assignments);

	                int operatorId = operateur.getId();

	                // Check if the operator has already been added
	                if (!operatorIds.contains(operatorId)) {
	                    operateurDTOs.add(OperateurDTO.fromEntity(operateur));
	                    operatorIds.add(operatorId);
	                }
	            }
	        }
	    }

	    return operateurDTOs;
	}
	@Override
	public List<OperateurFromExcel> save(List<OperateurFromExcel> dtos ,Integer idDemande) {
	    List<OperateurFromExcel> savedDTOs = new ArrayList<>();
	    Optional<DemandeDeRecrutement> demandeRecrutement = demandeRecrutementRepository.findById(idDemande);
	    for (OperateurFromExcel dto : dtos) {
	        // Vérifiez d'abord si l'opérateur existe déjà en utilisant son matricule
	        Optional<Operateurs> existingOperateur = operateurRepository.findByMatricule(dto.getMatricule());

	        if (!existingOperateur.isPresent()) {
	            // L'opérateur n'existe pas encore, alors nous le créons
	            Operateurs operateur = new Operateurs();
	            operateur.setNomOperateur(dto.getNomOperateur());
	            operateur.setMatricule(dto.getMatricule());
	            operateur.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	            operateur.setDemande(demandeRecrutement.get());
	            operateur.setQualification("Qualifié");
	            demandeRecrutement.get().setNombre(demandeRecrutement.get().getNombre()-1);
	            if(demandeRecrutement.get().getNombre() == 0) {
	            	demandeRecrutement.get().setEtat(EtatDemandeDeFormation.Fermé);
	            }
	            operateurRepository.save(operateur);

	            // Recherchez la ligne associée à l'opérateur
	            Optional<Ligne> ligne = ligneRepository.findByNomLigne(dto.getNomLigne());

	            if (ligne.isPresent()) {
	                // La ligne existe, nous pouvons donc créer une nouvelle instance de FIF et l'associer à l'opérateur
	                
	            	
	                // Parcourez les opérations de la ligne pour trouver celle correspondant au DTO
	                //for (Operation operation : ligne.get().getOperations()) {
	                    if (demandeRecrutement.get().getPosteAPouvoir()!= null) {
	                        // L'opération correspondante a été trouvée, nous pouvons maintenant créer une instance d'OperateurOperationAssignment et l'associer à l'opération et à l'opérateur
	                        OperateurOperationAssignment ooa = new OperateurOperationAssignment();
	                        ooa.setOperation(demandeRecrutement.get().getPosteAPouvoir());
	                        ooa.setOperateurs(operateur);
	                        ooar.save(ooa);
	                        Evaluation evaluation = new Evaluation();
	                        evaluation.setOperateurs(operateur);
	                        evaluation.setOperation(demandeRecrutement.get().getPosteAPouvoir());
	                        evaluation.setFormation(demandeRecrutement.get().getFormation());
	                        evaluation.setEvaluationTotal("0");
	                        evaluation.setEvaluationParAgentFormation("0");
	                        evaluation.setEvaluationParChefDeLigne("0");
	                        evaluation.setEvaluationParIngénieurQualité("0");
	                        evaluation.setEvaluationParMaintenance("0");
	                        evaluationRepository.save(evaluation);
	                    }
	               // }
	            } else {
	                // Gérer le cas où la ligne n'existe pas
	                // Vous pouvez choisir de lever une exception, enregistrer un journal, ou gérer différemment selon vos besoins
	                // Par exemple : logger.error("La ligne avec le nom {} n'existe pas.", dto.getNomLigne());
	            }
	        }

	        // Ajoutez le DTO actuel à la liste des DTOs sauvegardés
	        savedDTOs.add(dto);
	    }

	    return savedDTOs;
	}

	
	@Override
	public List<OperateurDTO> updateEtat(List<OperateurDTO> dtos) {
	    // Retrieve all OperateurDTOs from the database
	    List<OperateurDTO> operateurDtos = operateurRepository.findAll().stream()
	            .map(OperateurDTO::fromEntity)
	            .collect(Collectors.toList());
	    List<OperateurDTO> savedDTOs = new ArrayList<>();

	    operateurDtos.forEach(operateurDto -> {
	        // Find OperateurDTO object with matching matricule in dtos list
	        Optional<OperateurDTO> matchingOperateur = dtos.stream()
	            .filter(dto -> dto.getMatriculeOperateur().equals(operateurDto.getMatriculeOperateur()))
	            .findFirst();

	        // If matching OperateurDTO is found, set its etat
	        matchingOperateur.ifPresent(match -> {operateurDto.setEtat(match.getEtat());operateurDto.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	        operateurRepository.save(OperateurDTO.toEntity(operateurDto));});
	        
	        // Add updated OperateurDTO to the list of saved OperateurDTOs
	        savedDTOs.add(operateurDto);
	    });
	    
	    return savedDTOs; // Return the list of saved OperateurDTOs
	}

	@Override
	public OperateurDTO updateImage(String image, Integer idOperateur) {
		// TODO Auto-generated method stub
		Optional<Operateurs>operateur =operateurRepository.findById(idOperateur);
		operateur.get().setImage(image);
		
		return OperateurDTO.fromEntity(operateurRepository.save(operateur.get()));
		
	}

	@Override
	public OperateurDTO updateOperation(Integer idOperation, Integer idOperateur) {
	    Optional<Operation> operation = operationRepository.findById(idOperation);
	    Optional<Operateurs> operateur = operateurRepository.findById(idOperateur);

	    OperateurOperationAssignment ooa = new OperateurOperationAssignment();
	    ooa.setOperation(operation.get());
	    ooa.setOperateurs(operateur.get());
	    ooa.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	    ooar.save(ooa);

	    // Assuming setAssignments(List<OperateurOperationAssignment> assignments) method exists
	    List<OperateurOperationAssignment> assignments = new ArrayList<>();
	    assignments.add(ooa);

	    // Set the assignments
	    operateur.get().setAssignments(assignments);
	    operation.get().setAssignments(assignments);
	    Optional<DemandeDeRecrutement> demandeRecrutement = demandeRecrutementRepository.findById(operateur.get().getDemande().getId());

	    // set Evaluation
	   /* Evaluation evalution = new Evaluation();
	    evalution.setOperateurs(operateur.get());
	    evalution.setOperation(operation.get());
	    evalution.setFormation(demandeRecrutement.get().getFormation());
	    evalution.setEvaluationTotal("0");
	    evalution.setEvaluationParAgentFormation("0");
        evalution.setEvaluationParChefDeLigne("0");
        evalution.setEvaluationParIngénieurQualité("0");
        evalution.setEvaluationParMaintenance("0");
	    evaluationRepository.save(evalution);
	    // Save changes to repositories*/
	    operationRepository.save(operation.get());

	    // Save and return OperateurDTO
	    return OperateurDTO.fromEntity(operateurRepository.save(operateur.get()));
	}
	
	@Override
	public OperateurOperationAssignmentDTO updateCodeFormation(String CodeFormation, Integer id) {
		// TODO Auto-generated method stub
		Optional<OperateurOperationAssignment>ooa =ooar.findById(id);
		ooa.get().setCodeFormation(CodeFormation);
		return OperateurOperationAssignmentDTO.fromEntity(ooar.save(ooa.get()));
	}

	@Override
	public OperateurOperationAssignmentDTO updateMethodeSuivi(String methode, Integer id) {
		// TODO Auto-generated method stub
		Optional<OperateurOperationAssignment>ooa =ooar.findById(id);
		ooa.get().setMethodeSuivi(methode);
		return OperateurOperationAssignmentDTO.fromEntity(ooar.save(ooa.get()));
	}

	@Override
	public OperateurOperationAssignmentDTO updateDocumentIdentification(String Document, Integer id) {
		// TODO Auto-generated method stub
		Optional<OperateurOperationAssignment>ooa =ooar.findById(id);
		ooa.get().setDocumentIdentification(Document);
		return OperateurOperationAssignmentDTO.fromEntity(ooar.save(ooa.get()));
	}

	@Override
	public OperateurDTO updateQualification(String qualification, Integer idOperateur) {
		// TODO Auto-generated method stub
		Optional<Operateurs>operateur =operateurRepository.findById(idOperateur);
		operateur.get().setQualification(qualification);;
		
		return OperateurDTO.fromEntity(operateurRepository.save(operateur.get()));	}

	@Override
	public OperateurDTO updateTel(String telephone, Integer idOperateur) {
		// TODO Auto-generated method stub
		Optional<Operateurs>operateur =operateurRepository.findById(idOperateur);
		operateur.get().setTelephone(telephone);
		return OperateurDTO.fromEntity(operateurRepository.save(operateur.get()));	
	}


}

