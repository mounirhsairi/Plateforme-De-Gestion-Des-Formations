package com.pfe.Back.service;

import java.util.List;

import com.pfe.Back.DTO.EvaluationDTO;

public interface EvaluationService {

	List<EvaluationDTO> findAll();
	EvaluationDTO updateEValuationAgentFormation(Integer idEvaluation ,String evaluatioAgentFormation);
	EvaluationDTO updateEValuationChefDeLigne(Integer idEvaluation ,String evaluationChefDeLigne);
	EvaluationDTO updateEValuationMaintenance(Integer idEvaluation ,String evaluationMaintenance);
	EvaluationDTO updateEValuationIngenieurQualite(Integer idEvaluation ,String evaluationIngenieurQualite);
	EvaluationDTO updateEValuationRR(Integer idEvaluation ,String evaluationRR);

}
