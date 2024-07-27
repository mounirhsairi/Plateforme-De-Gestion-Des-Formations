package com.pfe.Back.service;

import java.util.List;

import com.pfe.Back.DTO.SessionFormationDTO;
import com.pfe.Back.DTO.programFormationDTO;

public interface programFormationService {
	
	
	programFormationDTO save(programFormationDTO dto);
	List<programFormationDTO> findAll();
	programFormationDTO findById(Integer id);




}
