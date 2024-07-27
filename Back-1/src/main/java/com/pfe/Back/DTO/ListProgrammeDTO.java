package com.pfe.Back.DTO;

import com.pfe.Back.model.ListProgramme;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ListProgrammeDTO {

	
	private Integer id ;
    private String element;

    
    public static ListProgrammeDTO fromEntity(ListProgramme ListProgramme) {
    	if(ListProgramme == null) {
    		return null ;
    	}
    	return ListProgrammeDTO.builder()
    			.element(ListProgramme.getElement())
    			.id(ListProgramme.getId())
    			.build();
    }
  public static ListProgramme toEntity(ListProgrammeDTO ListDTO)
  {
	  ListProgramme list = new ListProgramme();
	  list.setElement(ListDTO.getElement());
	  list.setId(ListDTO.getId());
	  return list ;
  }
}
