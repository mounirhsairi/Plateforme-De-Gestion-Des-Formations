package com.pfe.Back.DTO;

import com.pfe.Back.model.Ligne;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ligneDTO {
	private String nomLigne ;
	private Integer id ;
	public static ligneDTO fromEntity(Ligne ligne) {
    	if(ligne == null) {
    		return null ;
    	}
    	return ligneDTO.builder()
    			.nomLigne(ligne.getNomLigne())
    			.id(ligne.getId())
    			.build();
    }
  public static Ligne toEntity(ligneDTO ligneDTO)
  {
	  Ligne ligne = new Ligne();
	  ligne.setNomLigne(ligneDTO.getNomLigne());
	  ligne.setId(ligneDTO.getId());
	  return ligne ;
  }
}
