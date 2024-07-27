package com.pfe.Back.service.impl;

import java.util.List; 
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pfe.Back.repository.ligneRepository;
import com.pfe.Back.DTO.ligneDTO;
import com.pfe.Back.DTO.opeartionDto;
import com.pfe.Back.model.Ligne;
import com.pfe.Back.model.Operation;
import com.pfe.Back.repository.operationRepository;
import com.pfe.Back.service.operationService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class operationServiceImpl implements operationService {
	
	private final operationRepository operationR ;
	private final ligneRepository ligneRepository ;
	@Autowired
	public operationServiceImpl(operationRepository operationR, ligneRepository ligneRepository) {
		this.operationR = operationR;
		this.ligneRepository = ligneRepository;
	}
	@Override
	public opeartionDto save(opeartionDto dto ,Integer idLigne) {
		// TODO Auto-generated method stub
		Optional<Ligne>ligne =ligneRepository.findById(idLigne);
		dto.setLigne(ligneDTO.fromEntity(ligne.get()));
		return opeartionDto.fromEntity(operationR.save(opeartionDto.toEntity(dto)));
	}

	

	@Override
	public opeartionDto findById(Integer id) {
		if(id == null) {
			log.error("operation id is null");
		}
		// TODO Auto-generated method stub
		Optional <Operation> opera = operationR.findById(id);
		return Optional.of(opeartionDto.fromEntity(opera.get())).orElseThrow() ;
	}

	@Override
	public List<opeartionDto> findAllByLigneId(Integer id) {
		// TODO Auto-generated method stub
		if(id == null) {
			log.error("ligne id is null");
		}
		
		return operationR.findAllByLigneId(id).stream().map(opeartionDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<opeartionDto> findAll() {
		// TODO Auto-generated method stub
		return operationR.findAll().stream().map(opeartionDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if(id == null) {
			log.error("ligne id is null");
		}

		operationR.deleteById(id);
	}

}
