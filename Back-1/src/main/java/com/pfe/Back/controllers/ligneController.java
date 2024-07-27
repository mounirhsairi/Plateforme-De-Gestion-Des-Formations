package com.pfe.Back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.ligneDTO;
import com.pfe.Back.controllers.api.ligneApi;
import com.pfe.Back.service.ligneService;
@CrossOrigin(origins="*")
@RestController
public class ligneController implements ligneApi {

	private ligneService ligneServ ;
	
	@Autowired
	public ligneController(ligneService ligneServ) {
		this.ligneServ = ligneServ;
	}

	@Override
	public ligneDTO save(ligneDTO dto) {
		// TODO Auto-generated method stub
		return ligneServ.save(dto);
	}

	@Override
	public ligneDTO findById(Integer id) {
		
		// TODO Auto-generated method stub
		return ligneServ.findById(id);
	}

	@Override
	public List<ligneDTO> findAll() {
		// TODO Auto-generated method stub
		return ligneServ.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		ligneServ.delete(id);
	}

}
