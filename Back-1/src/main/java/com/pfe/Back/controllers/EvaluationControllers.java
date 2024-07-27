package com.pfe.Back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.EvaluationDTO;
import com.pfe.Back.controllers.api.EvaluationApi;
import com.pfe.Back.service.EvaluationService;
@CrossOrigin(origins="*")

@RestController
public class EvaluationControllers implements EvaluationApi {

	@Autowired
	EvaluationService service ;
	@Override
	public List<EvaluationDTO> findAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}
	@Override
	public EvaluationDTO updateEValuationAgentFormation(Integer idEvaluation, String evaluatioAgentFormation) {
		// TODO Auto-generated method stub
		return service.updateEValuationAgentFormation(idEvaluation, evaluatioAgentFormation);
	}
	@Override
	public EvaluationDTO updateEValuationChefDeLigne(Integer idEvaluation, String evaluationChefDeLigne) {
		// TODO Auto-generated method stub
		return service.updateEValuationChefDeLigne(idEvaluation, evaluationChefDeLigne);
	}
	@Override
	public EvaluationDTO updateEValuationMaintenance(Integer idEvaluation, String evaluationMaintenance) {
		// TODO Auto-generated method stub
		return service.updateEValuationMaintenance(idEvaluation, evaluationMaintenance);
	}
	@Override
	public EvaluationDTO updateEValuationIngenieurQualite(Integer idEvaluation, String evaluationIngenieurQualite) {
		// TODO Auto-generated method stub
		return service.updateEValuationIngenieurQualite(idEvaluation, evaluationIngenieurQualite);
	}
	@Override
	public EvaluationDTO updateEValuationRR(Integer idEvaluation, String evaluationRR) {
		// TODO Auto-generated method stub
		return service.updateEValuationRR(idEvaluation, evaluationRR);
	}

}
