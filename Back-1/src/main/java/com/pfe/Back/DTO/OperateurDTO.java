package com.pfe.Back.DTO;



import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.Back.model.DemandeDeRecrutement;
import com.pfe.Back.model.FIF;
import com.pfe.Back.model.OperateurOperationAssignment;
import com.pfe.Back.model.Operateurs;

import lombok.Builder;
import lombok.Data;



@Builder
@Data
public class OperateurDTO {
	private Integer id;
	private String MatriculeOperateur ;
	private String NomOperateur ;
	private String telephone;
	private String Etat;
	private String UAP;
    private String image; // New image attribute
	private String Chaine;
    private List<OperateurOperationAssignmentDTO> assignments;
    private  List<fifDTO> pdfFiles;
	private String creationDate ;
	private Integer idDemande;
	private String qualification ;

	@JsonIgnore
	private DemandeDeRecrutement demandeRecrutement ;
	//private List<EvaluationDTO> Evaluation ;
	//private List<String> EvaluationTotal ;


    public static OperateurDTO fromEntity(Operateurs operateurs) {
    	if(operateurs == null) {
    		return null ;
    	}
    	
    	return OperateurDTO.builder()
    			.id(operateurs.getId())
    			 .MatriculeOperateur(operateurs.getMatricule())
    			.NomOperateur(operateurs.getNomOperateur())
    			.telephone(operateurs.getTelephone())
    			.assignments(operateurs.getAssignments().stream().map(OperateurOperationAssignmentDTO::fromEntity).collect(Collectors.toList()))
    			.qualification(operateurs.getQualification() != null ? operateurs.getQualification():"")
    			//.idDemande(operateurs.getDemande().getId())

    			.Etat(operateurs.getEtat())
    			.UAP(operateurs.getUAP())
    			.Chaine(operateurs.getChaine())
    			.image(operateurs.getImage())
    			.creationDate(operateurs.getCreationDate())
    			.pdfFiles(operateurs.getPdfFiles().stream().map(fifDTO::fromEntity).collect(Collectors.toList()))
    			//.Evaluation(operateurs.getEvalution().stream().map(EvaluationDTO::fromEntity).collect(Collectors.toList()))
    			.build();
    }
  public static Operateurs toEntity(OperateurDTO operateurDTO)
  {
	  Operateurs operateur = new Operateurs();
	  DemandeDeRecrutement demande = new DemandeDeRecrutement();
	  operateur.setMatricule(operateurDTO.getMatriculeOperateur());
	  operateur.setNomOperateur(operateurDTO.getNomOperateur());
	  operateur.setTelephone(operateurDTO.getTelephone());
	  operateur.setEtat(operateurDTO.getEtat());
	  operateur.setUAP(operateurDTO.getUAP());
	  demande.setId(operateurDTO.getIdDemande());
	  //operateur.setDemande(demande);
	  operateur.setChaine(operateurDTO.getChaine());
	  operateur.setImage(operateurDTO.getImage());
	  operateur.setId(operateurDTO.getId());
	  List<OperateurOperationAssignment> assignments = new ArrayList<>();
	    if (operateurDTO.getAssignments() != null) {
	        assignments = operateurDTO.getAssignments().stream()
	                .map(OperateurOperationAssignmentDTO::toEntity)
	                .collect(Collectors.toList());
	    }
	    operateur.setAssignments(assignments);
	  return operateur ;
  }
}
