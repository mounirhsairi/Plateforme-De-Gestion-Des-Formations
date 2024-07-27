package com.pfe.Back.DTO;

import com.pfe.Back.model.FIF;


import lombok.Builder;
import lombok.Data;

import com.pfe.Back.model.Operateurs;



@Builder
@Data
public class fifDTO {

	private String fileName;
    private Integer idOperateur;
    private String data;

    public static fifDTO fromEntity(FIF files) {
    	
    	return fifDTO.builder()
    			.fileName(files.getFileName())
    			.idOperateur(files.getOperateurs().getId())
    			.data(files.getData())
    			.build();
    	
    }
    public static FIF toEntity(fifDTO dto) {
    	
    	FIF files = new FIF();
    	files.setFileName(dto.getFileName());
    	files.setData(dto.getData());
    	Operateurs operateur = new Operateurs();
    	operateur.setId(dto.getIdOperateur());
    	files.setOperateurs(operateur);
    	
    	return files ;
    }
    

}
