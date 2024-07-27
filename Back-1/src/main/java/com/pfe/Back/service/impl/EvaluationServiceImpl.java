package com.pfe.Back.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pfe.Back.DTO.EvaluationDTO;
import com.pfe.Back.model.Evaluation;
import com.pfe.Back.repository.EvaluationRepository;
import com.pfe.Back.service.EvaluationService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EvaluationServiceImpl implements EvaluationService  {
	
	@Autowired
	EvaluationRepository repository ;
	
	@Override
	public List<EvaluationDTO> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll().stream().map(EvaluationDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public EvaluationDTO updateEValuationAgentFormation(Integer idEvaluation, String evaluatioAgentFormation) {
		// TODO Auto-generated method stub
		Optional<Evaluation> evaluation =repository.findById(idEvaluation);
		evaluation.get().setEvaluationParAgentFormation(evaluatioAgentFormation);
		evaluation.get().setEvaluationTotal(String.valueOf(( Integer.parseInt(evaluatioAgentFormation) +Integer.parseInt(evaluation.get().getEvaluationParChefDeLigne())+Integer.parseInt(evaluation.get().getEvaluationParIngénieurQualité())+Integer.parseInt(evaluation.get().getEvaluationParMaintenance()))/4));
		return EvaluationDTO.fromEntity(repository.save(evaluation.get()));
	}

	@Override
	public EvaluationDTO updateEValuationChefDeLigne(Integer idEvaluation, String evaluationChefDeLigne) {
		// TODO Auto-generated method stub
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String loggedInUser = authentication.getName();
		Optional<Evaluation> evaluation =repository.findById(idEvaluation);
		evaluation.get().setEvaluationParChefDeLigne(evaluationChefDeLigne);
		evaluation.get().setModifiedByChefDeLigne(loggedInUser);
		evaluation.get().setEvaluationTotal(String.valueOf(( Integer.parseInt(evaluationChefDeLigne) +Integer.parseInt(evaluation.get().getEvaluationParMaintenance())+Integer.parseInt(evaluation.get().getEvaluationParIngénieurQualité())+Integer.parseInt(evaluation.get().getEvaluationParAgentFormation()))/4));
		return EvaluationDTO.fromEntity(repository.save(evaluation.get()));
	}

	@Override
	public EvaluationDTO updateEValuationMaintenance(Integer idEvaluation, String evaluationMaintenance) {
		// TODO Auto-generated method stub
		Optional<Evaluation> evaluation =repository.findById(idEvaluation);
		evaluation.get().setEvaluationParMaintenance(evaluationMaintenance);
		evaluation.get().setEvaluationTotal(String.valueOf(( Integer.parseInt(evaluationMaintenance) +Integer.parseInt(evaluation.get().getEvaluationParChefDeLigne())+Integer.parseInt(evaluation.get().getEvaluationParIngénieurQualité())+Integer.parseInt(evaluation.get().getEvaluationParAgentFormation()))/4));

		return EvaluationDTO.fromEntity(repository.save(evaluation.get()));
	}

	@Override
	public EvaluationDTO updateEValuationIngenieurQualite(Integer idEvaluation, String evaluationIngenieurQualite) {
		// TODO Auto-generated method stub
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String loggedInUser = authentication.getName();
		Optional<Evaluation> evaluation =repository.findById(idEvaluation);
		evaluation.get().setEvaluationParIngénieurQualité(evaluationIngenieurQualite);
		evaluation.get().setModifiedByIngenieurQualite(loggedInUser);
		evaluation.get().setEvaluationTotal(String.valueOf(( Integer.parseInt(evaluationIngenieurQualite) +Integer.parseInt(evaluation.get().getEvaluationParChefDeLigne())+Integer.parseInt(evaluation.get().getEvaluationParMaintenance())+Integer.parseInt(evaluation.get().getEvaluationParAgentFormation()))/4));
		return EvaluationDTO.fromEntity(repository.save(evaluation.get()));
	}

	@Override
	public EvaluationDTO updateEValuationRR(Integer idEvaluation, String evaluationRR) {
		// TODO Auto-generated method stub
		Optional<Evaluation> evaluation =repository.findById(idEvaluation);
		evaluation.get().setEvaluationParMaintenanceRAndR(evaluationRR);

		return EvaluationDTO.fromEntity(repository.save(evaluation.get()));
	}

}
