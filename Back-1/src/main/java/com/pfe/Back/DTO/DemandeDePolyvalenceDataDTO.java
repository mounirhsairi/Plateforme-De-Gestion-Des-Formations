package com.pfe.Back.DTO;

import java.util.Date;


import com.pfe.Back.model.DemandeDePolyvalenceData;
import com.pfe.Back.model.DemandeDePolyvalence;

import com.pfe.Back.model.Ligne;
import com.pfe.Back.model.Operation;
import com.pfe.Back.model.Operateurs;


import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class DemandeDePolyvalenceDataDTO {

	private Integer id ;
	private Integer idLigneActuel ;
	private Integer idOperateur;
	
	private String NomOperateur ;
	private String NomLigneActuel ;
	private String NomNouvelleLigne ;
	private String NomNouvelleOperation ;
	
	private Integer idNouvelleLigne ;
	private Integer idNouvelleOperation ;

	private Integer idDemandeDePolyvalence;
	private Date DelaiLimite ;
	private String Objectifs ;

	private String cause ;
	
	private String dateFin;
	private String dateDebut;

	
    public static DemandeDePolyvalenceDataDTO fromEntity(DemandeDePolyvalenceData demande) {
    	
    	return DemandeDePolyvalenceDataDTO.builder()
    			.id(demande.getId())
    			.dateDebut(demande.getDateDebut())
    			.dateFin(demande.getDateFin())
    			.idOperateur(demande.getOperateurs().getId())
    			.idLigneActuel(demande.getLigneActuel().getId())
    			.idNouvelleLigne(demande.getNouvelleLigne().getId())
    			.idNouvelleOperation(demande.getNouvelleOperation().getId())
    			.NomOperateur(demande.getOperateurs().getNomOperateur())
    			.NomLigneActuel(demande.getLigneActuel().getNomLigne())
    			.NomNouvelleLigne(demande.getNouvelleLigne().getNomLigne())
    			.NomNouvelleOperation(demande.getNouvelleOperation().getOperationName())
    			.DelaiLimite(demande.getDelaiLimite())
    			.cause(demande.getCause())
    			.Objectifs(demande.getObjectifs())
    			.idDemandeDePolyvalence(demande.getDemande().getId())
    			.build();
    }
    public static DemandeDePolyvalenceData toEntity(DemandeDePolyvalenceDataDTO dto) {
    	
    	DemandeDePolyvalenceData demande = new DemandeDePolyvalenceData();
    	Operation operation = new Operation();
    	Ligne ligne = new Ligne();
    	Ligne NouvelleLigne = new Ligne();
    	Operateurs operateur =new Operateurs();
    	operation.setId(dto.getIdNouvelleOperation());
    	operateur.setId(dto.getIdOperateur());;
    	NouvelleLigne.setId(dto.getIdNouvelleLigne());
    	ligne.setId(dto.getIdLigneActuel());
    	demande.setLigneActuel(ligne);
    	demande.setId(dto.getId());
    	demande.setNouvelleLigne(NouvelleLigne);
    	demande.setNouvelleOperation(operation);
    	demande.setOperateurs(operateur);
    	DemandeDePolyvalence data= new DemandeDePolyvalence();
    	data.setId(dto.getIdDemandeDePolyvalence());
    	demande.setDemande(data);
    	demande.setCause(dto.getCause());
    	demande.setObjectifs(dto.getObjectifs());
    	demande.setDelaiLimite(dto.getDelaiLimite());
    	demande.setId(dto.getId());
    	demande.setDateDebut(dto.getDateDebut());
    	demande.setDateFin(dto.getDateFin());
		return demande;
    	
    }
}
