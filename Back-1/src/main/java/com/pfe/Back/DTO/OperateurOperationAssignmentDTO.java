package com.pfe.Back.DTO;


import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.pfe.Back.model.Ligne;

import com.pfe.Back.model.OperateurOperationAssignment;
import com.pfe.Back.model.Operateurs;
import com.pfe.Back.model.Operation;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class OperateurOperationAssignmentDTO {
	private Integer idOperateurs;
    private Integer idOperation;
    private Instant startDate;
    private Instant endDate;
    private String OperationName ;
    private String NomLigne;
    private String codeFormation;
    private String methodeSuivi ;
	private String creationDate ;

    private String documentIdentification ;

    private Integer idLigne ;
    private Integer id ;
	//private Operation operation ;
    private List<EvaluationDTO> evaluation ;

    
    public static OperateurOperationAssignmentDTO fromEntity(OperateurOperationAssignment operationASS) {
    	if(operationASS == null) {
    		return null ;
    	}
    	
    	return OperateurOperationAssignmentDTO.builder()
    			.id(operationASS.getId())
    			.creationDate(operationASS.getCreationDate() != null ? operationASS.getCreationDate() : "" )
                .codeFormation(operationASS.getCodeFormation() != null ? operationASS.getCodeFormation() : "default_value")
    			.methodeSuivi(operationASS.getMethodeSuivi() != null ? operationASS.getMethodeSuivi() :  "default_value")
    			.documentIdentification(operationASS.getDocumentIdentification()!= null ? operationASS.getDocumentIdentification() :  "default_value")
    			.idOperateurs(operationASS.getOperateurs().getId())
    			.idOperation(operationASS.getOperation()!= null ? operationASS.getOperation().getId():null)
    			.startDate(operationASS.getStartDate())
    			.idLigne(operationASS.getOperation()!= null ? operationASS.getOperation().getLigne().getId():null)
    			.endDate(operationASS.getEndDate())
    			//.operation(operationASS.getOperation())
    			.OperationName(operationASS.getOperation()!= null ? operationASS.getOperation().getOperationName():null)
    			.NomLigne(operationASS.getOperation()!= null ? operationASS.getOperation().getLigne().getNomLigne():null)
    			//.idLigne(operationASS.getOperation().getLigne().getId())
    			.evaluation(operationASS.getOperateurs().getEvalution().stream().map(EvaluationDTO::fromEntity).collect(Collectors.toList()))
    			.build();
    	
    }
  public static OperateurOperationAssignment toEntity(OperateurOperationAssignmentDTO dto)
  {
	  OperateurOperationAssignment operateurOperationAss = new OperateurOperationAssignment();
	    Operateurs operateurs = new Operateurs();
	    operateurs.setId(dto.getIdOperateurs());
	    Operation operation = new Operation();
	    operation.setId(dto.getIdOperation());
	    //Ligne ligne = new Ligne();
	    //ligne.setNomLigne(dto.getNomLigne());
	    //ligne.setId(dto.getIdLigne());
	    operateurOperationAss.setOperateurs(operateurs);
	    operateurOperationAss.setOperation(operation);
	    operateurOperationAss.setStartDate(dto.getStartDate());
	    operateurOperationAss.setEndDate(dto.getEndDate());
	    return operateurOperationAss;
  }
  
}
