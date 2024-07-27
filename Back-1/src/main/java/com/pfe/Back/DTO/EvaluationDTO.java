package com.pfe.Back.DTO;

import com.pfe.Back.model.Evaluation;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EvaluationDTO {

	private String EvaluationParAgentFormation ;
	
	private String EvaluationParChefDeLigne ;
	
	private String evaluationParIngenieurQualite ;
	
	private String EvaluationParMaintenance ;

	private String EvaluationParMaintenanceRAndR ;

	private String EvaluationTotal;

	private Integer idOperateur ;
	private Integer id ;

	private Integer idOperation ;
	private String nomOperation ;
	private String nomOperateur ;

	private Integer idLigne ;
	private Integer idFormation ;
	private String MatriculeFormation ;
	private String MatriculeOperateur ;
	private String TypeFormation ;
	
	 private String modifiedByChefDeLigne;
	 private String modifiedByIngenieurQualite;

	public static EvaluationDTO fromEntity(Evaluation evalution) {
		if(evalution.getEvaluationTotal()==null) {
			evalution.setEvaluationTotal("0");
		}
		
		return EvaluationDTO.builder()
				.EvaluationParAgentFormation(evalution.getEvaluationParAgentFormation())
				.EvaluationParMaintenanceRAndR(evalution.getEvaluationParMaintenanceRAndR() != null ? evalution.getEvaluationParMaintenanceRAndR(): "0")
				.EvaluationParChefDeLigne(evalution.getEvaluationParChefDeLigne())
				.evaluationParIngenieurQualite(evalution.getEvaluationParIngénieurQualité())
				.EvaluationParMaintenance(evalution.getEvaluationParMaintenance())
				.EvaluationTotal(evalution.getEvaluationTotal())
				.idOperateur(evalution.getOperateurs().getId())
				.idOperation(evalution.getOperation() != null ? evalution.getOperation().getId():null)
				.nomOperation(evalution.getOperation() != null ? evalution.getOperation().getOperationName():null)
				.idLigne(evalution.getOperation() != null ? evalution.getOperation().getLigne().getId():null)
				.MatriculeFormation(evalution.getFormation() != null ? evalution.getFormation().getMatriculeFormation() : null)
				.MatriculeOperateur(evalution.getOperateurs().getMatricule())
				.nomOperateur(evalution.getOperateurs().getNomOperateur())

			    .TypeFormation(evalution.getFormation() != null ? evalution.getFormation().getTypeDeFormation() : null)
				.id(evalution.getId())
				.idFormation(evalution.getFormation().getId())
				.modifiedByIngenieurQualite(evalution.getModifiedByIngenieurQualite())
				.modifiedByChefDeLigne(evalution.getModifiedByChefDeLigne())

				.build();
	}
	public static Evaluation toEntity(EvaluationDTO dto) {
		
		Evaluation evaluation = new Evaluation();
		evaluation.setEvaluationParAgentFormation(dto.getEvaluationParAgentFormation());
		evaluation.setEvaluationParChefDeLigne(dto.getEvaluationParChefDeLigne());
		evaluation.setEvaluationParIngénieurQualité(dto.getEvaluationParIngenieurQualite());
		evaluation.setEvaluationParMaintenance(dto.getEvaluationParMaintenance());
		evaluation.setEvaluationTotal(dto.getEvaluationTotal());
		
		
		
		return evaluation ;
	}
}
