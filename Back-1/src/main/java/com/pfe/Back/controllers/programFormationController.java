package com.pfe.Back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.SessionFormationDTO;
import com.pfe.Back.DTO.programFormationDTO;
import com.pfe.Back.controllers.api.programFormationApi;
import com.pfe.Back.model.SessionFormation;
import com.pfe.Back.service.programFormationService;
@CrossOrigin(origins="*")

@RestController

public class programFormationController implements programFormationApi {

	private programFormationService programService ;
	
	@Autowired
	public programFormationController(programFormationService programService) {
	
		this.programService = programService;
	}

	@Override
	public programFormationDTO save(programFormationDTO dto  ) {
		// TODO Auto-generated method stub
		return programService.save(dto);
	}

	@Override
	public List<programFormationDTO> findAll() {
		// TODO Auto-generated method stub
		return programService.findAll();
	}

	@Override
	public programFormationDTO findById(Integer id) {
		// TODO Auto-generated method stub
		return programService.findById(id);
	}

}
