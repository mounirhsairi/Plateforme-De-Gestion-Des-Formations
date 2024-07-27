package com.pfe.Back.service;

import java.util.List;

import com.pfe.Back.DTO.opeartionDto;

public interface operationService {
	opeartionDto save(opeartionDto dto ,Integer idLigne);
	opeartionDto findById(Integer id);
	List<opeartionDto> findAllByLigneId(Integer id);
	List<opeartionDto> findAll();
	void delete(Integer id );
}
