package com.pfe.Back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.opeartionDto;
import com.pfe.Back.controllers.api.opeartionApi;
import com.pfe.Back.service.operationService;
@CrossOrigin(origins="*")
@RestController
public class operationcontroller implements opeartionApi{

	private operationService operationServ ;
	@Autowired
	public operationcontroller(operationService operationServ) {
		this.operationServ = operationServ;
	}
	@Override
	public opeartionDto save(opeartionDto dto,Integer idLigne) {
		// TODO Auto-generated method stub
		return operationServ.save(dto, idLigne);
	}

	

	@Override
	public opeartionDto findById(Integer id) {
		// TODO Auto-generated method stub
		return operationServ.findById(id);
	}

	

	@Override
	public List<opeartionDto> findAll() {
		// TODO Auto-generated method stub
		return operationServ.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		operationServ.delete(id);
	}
	@Override
	public List<opeartionDto> findAllByLigneId(Integer id) {
		// TODO Auto-generated method stub
		return operationServ.findAllByLigneId(id);
	}

}
