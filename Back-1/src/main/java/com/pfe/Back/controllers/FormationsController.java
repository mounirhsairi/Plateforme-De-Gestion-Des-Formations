package com.pfe.Back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.FormationsDTO;
import com.pfe.Back.controllers.api.FormationsApi;
import com.pfe.Back.service.FormationsService;

@CrossOrigin(origins="*")

@RestController
public class FormationsController implements FormationsApi {

	@Autowired
	private FormationsService formationService ;
	
	
	@Override
	public FormationsDTO save(FormationsDTO dto) {
		// TODO Auto-generated method stub
		return formationService.save(dto);
	}


	@Override
	public List<FormationsDTO> findAll() {
		// TODO Auto-generated method stub
		return formationService.findAll();
	}

}
