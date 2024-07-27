package com.pfe.Back.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.ListProgrammeDTO;
import com.pfe.Back.DTO.SessionFormationDTO;
import com.pfe.Back.controllers.api.SessionFormationApi;
import com.pfe.Back.model.ListProgramme;
import com.pfe.Back.service.SessionFormationService;
@CrossOrigin(origins="*")

@RestController

public class SessionFormationController implements SessionFormationApi{

	
	private SessionFormationService sessionService ;
	@Autowired
	public SessionFormationController(SessionFormationService sessionService) {
		
		this.sessionService = sessionService;
	}
	
	@Override
	public SessionFormationDTO save(SessionFormationDTO dto,Integer idProgram) {
		// TODO Auto-generated method stub
		return sessionService.save(dto,idProgram);
	}

	

	@Override
	public List<SessionFormationDTO> findAll() {
		// TODO Auto-generated method stub
		return sessionService.findAll();
	}

	@Override
	public SessionFormationDTO updateListProgrammme(Integer idSession, String programme) {
		// TODO Auto-generated method stub
		return sessionService.updateListProgrammme(idSession, programme);
	}

	@Override
	public List<ListProgrammeDTO> findBySession_Id(Integer sessionId) {
		// TODO Auto-generated method stub
		return sessionService.findBySession_Id(sessionId);
	}

	@Override
	public void delete(Integer sessionId) {
		// TODO Auto-generated method stub
		sessionService.delete(sessionId);
		
	}

	@Override
	public void deleteProgramme(Integer id) {
		// TODO Auto-generated method stub
		sessionService.deleteProgramme(id);
	}

}
