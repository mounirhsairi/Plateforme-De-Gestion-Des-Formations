package com.pfe.Back.DTO;


import java.util.List;
import java.util.stream.Collectors;

import com.pfe.Back.model.DemandeDeRecrutement;
import com.pfe.Back.model.Evaluation;
import com.pfe.Back.model.Ligne;
import com.pfe.Back.model.Operation;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class opeartionDto {
	private String OperationName ;
	private Integer id;
	private Integer Effectifsformes;
    private ligneDTO ligne ;
    private  Integer idLigne;
	private List<EvaluationDTO> evaluation;
	
	public static opeartionDto fromEntity(Operation operation) {
    	if(operation == null) {
    		return null ;
    	}
    	
    	return opeartionDto.builder()
    			.OperationName(operation.getOperationName())
    			.idLigne(operation.getLigne().getId())
    			.id(operation.getId())
    			.ligne(ligneDTO.fromEntity(operation.getLigne()))
    			.Effectifsformes(operation.getAssignments()!= null ? operation.getAssignments().size():null)
    			.evaluation(operation.getEvalution()!= null ? operation.getEvalution().stream().map(EvaluationDTO::fromEntity).collect(Collectors.toList()):null)
    			.build();
    }
  public static Operation toEntity(opeartionDto operationDTO)
  {
	  Operation operation = new Operation();
	  operation.setOperationName(operationDTO.getOperationName());
	  operation.setLigne(ligneDTO.toEntity(operationDTO.getLigne()));
	  operation.setId(operationDTO.getId());
	  return operation ;
  }
}
