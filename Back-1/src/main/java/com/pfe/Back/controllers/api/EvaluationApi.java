package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pfe.Back.DTO.EvaluationDTO;

import io.swagger.annotations.Api;

@Api("/Evaluation")
public interface EvaluationApi {

	
	@GetMapping(value = "/Evaluation/all",produces = "application/json")

	List<EvaluationDTO> findAll();
	
	@PatchMapping(value ="/Evaluation/update/AgentFormation/{idEvaluation}/{evaluatioAgentFormation}")
	EvaluationDTO updateEValuationAgentFormation(@PathVariable ("idEvaluation")Integer idEvaluation,@PathVariable ("evaluatioAgentFormation") String evaluatioAgentFormation);
	
	@PatchMapping(value ="/Evaluation/update/ChefDeLigne/{idEvaluation}/{evaluationChefDeLigne}")
	EvaluationDTO updateEValuationChefDeLigne(@PathVariable ("idEvaluation")Integer idEvaluation,@PathVariable ("evaluationChefDeLigne") String evaluationChefDeLigne);

	@PatchMapping(value ="/Evaluation/update/Maintenance/{idEvaluation}/{evaluationMaintenance}")
	EvaluationDTO updateEValuationMaintenance(@PathVariable ("idEvaluation")Integer idEvaluation,@PathVariable ("evaluationMaintenance") String evaluationMaintenance);
	@PatchMapping(value ="/Evaluation/update/Qualité/{idEvaluation}/{evaluationIngenieurQualite}")
	public EvaluationDTO updateEValuationIngenieurQualite(@PathVariable ("idEvaluation")Integer idEvaluation,@PathVariable ("evaluationIngenieurQualite") String evaluationIngenieurQualite) ;
	
	@PatchMapping(value ="/Evaluation/update/RR/{idEvaluation}/{evaluationRR}")
	EvaluationDTO updateEValuationRR(@PathVariable ("idEvaluation") Integer idEvaluation,@PathVariable ("evaluationRR") String evaluationRR);
}
