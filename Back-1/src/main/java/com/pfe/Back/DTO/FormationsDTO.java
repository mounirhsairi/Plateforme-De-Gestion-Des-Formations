package com.pfe.Back.DTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.Back.model.DemandeDePolyvalence;
import com.pfe.Back.model.DemandeDePolyvalenceData;
import com.pfe.Back.model.DemandeDeRecrutement;
import com.pfe.Back.model.Formations;
import com.pfe.Back.model.Operateurs;
import com.pfe.Back.model.Operation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormationsDTO {

	private String MatriculeFormation ;
	private String TypeDeFormation ;
	private Integer id ;
	@JsonIgnore
    private DemandeDeRecrutement demandeFormation;
	@JsonIgnore
    private DemandeDePolyvalence demandePoly;
	
	private String LigneName ;
	private Integer idDemandeRecrutement ;

	private String OperationName ;
	private String creationDate ;
	private List<String>PolyDataList ;
	
	public static FormationsDTO fromEntity(Formations formation) {
		
	    FormationsDTO formationsDTO = FormationsDTO.builder()
	            .MatriculeFormation(formation.getMatriculeFormation())
	            .demandeFormation(formation.getDemandeFormation())
	            .TypeDeFormation(formation.getTypeDeFormation())
	            .creationDate(formation.getCreationDate())
	            .id(formation.getId())
	            .build();

	    if (formation.getDemandeFormation() != null && formation.getDemandeFormation().getPosteAPouvoir() != null) {
	        formationsDTO.setOperationName(formation.getDemandeFormation().getPosteAPouvoir().getOperationName());
	        formationsDTO.setLigneName(formation.getDemandeFormation().getPosteAPouvoir().getLigne().getNomLigne());
	        formationsDTO.setIdDemandeRecrutement(formation.getDemandeFormation().getId());
        	List<String> demandeDePolyvalenceOperateurList  = new ArrayList<>();;

	        for(Operateurs operateur :formationsDTO.getDemandeFormation().getOperateurs()) {
	    		demandeDePolyvalenceOperateurList.add(operateur.getMatricule());
	        }
    		formationsDTO.setPolyDataList(demandeDePolyvalenceOperateurList);

	    }
	    if (formation.getDemandePoly() != null && formation.getDemandePoly().getDemandDataList() != null) {
	        List<String> demandeDePolyvalenceOperateurList = new ArrayList<>();
	        for (DemandeDePolyvalenceData demandData : formation.getDemandePoly().getDemandDataList()) {
	            formationsDTO.setOperationName(demandData.getNouvelleOperation().getOperationName());
	            formationsDTO.setLigneName(demandData.getNouvelleLigne().getNomLigne());
	            demandeDePolyvalenceOperateurList.add(demandData.getOperateurs().getMatricule());
	        }
	        // Ajouter tous les opérateurs à votre objet formationsDTO
	        formationsDTO.setPolyDataList(demandeDePolyvalenceOperateurList);
	    

	    	
	        
	        
	        
	    }
	    
	    return formationsDTO;
	}

	public static Formations toEntity (FormationsDTO dto) {
		Formations formation = new Formations();
		formation.setMatriculeFormation("F" + String.valueOf(Instant.now().toEpochMilli()).substring(10));
		formation.setTypeDeFormation(dto.getTypeDeFormation());
		Operation operation = new Operation();
		operation.setOperationName(dto.getOperationName());
		DemandeDeRecrutement demande = new DemandeDeRecrutement();
		demande.setPosteAPouvoir(operation);
		formation.setDemandeFormation(demande);
		
		
		
		return formation ;
	}
}
