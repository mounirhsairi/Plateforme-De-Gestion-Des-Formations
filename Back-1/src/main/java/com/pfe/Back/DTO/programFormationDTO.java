package com.pfe.Back.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfe.Back.model.programFormation;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class programFormationDTO {
	
	private Integer id ;
	private String nom ;
    private List<SessionFormationDTO> sessionFormation;

    
    public static programFormationDTO fromEntity(programFormation program) {
    	if(program == null) {
    		return null ;
    	}
    	
    	return programFormationDTO.builder()
    			.id(program.getId())
    			.nom(program.getNom())
    			.sessionFormation(program.getSessionFormation()!= null ? program.getSessionFormation().stream().map(SessionFormationDTO::fromEntity).collect(Collectors.toList()):null)
    			.build();
    }
  public static programFormation toEntity(programFormationDTO programDTO)
  {
	  programFormation program = new programFormation();
	  
	  program.setNom(programDTO.getNom());
	  program.setId(programDTO.getId());
	  return program ;
  }


}
