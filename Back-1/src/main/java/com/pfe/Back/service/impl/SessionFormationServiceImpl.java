package com.pfe.Back.service.impl;

import java.util.List ;
import java.util.ArrayList;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pfe.Back.model.ListProgramme;
import com.pfe.Back.DTO.ListProgrammeDTO;
import com.pfe.Back.DTO.SessionFormationDTO;
import com.pfe.Back.model.SessionFormation;
import com.pfe.Back.model.programFormation;
import com.pfe.Back.repository.ListProgrammeRepository;
import com.pfe.Back.repository.SessionFormationRepository;
import com.pfe.Back.repository.programFormationRepository;
import com.pfe.Back.service.SessionFormationService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class SessionFormationServiceImpl implements SessionFormationService {

	
	private final programFormationRepository programRepository ;

	private final SessionFormationRepository sessionRepository ;
	
	private final ListProgrammeRepository programListRepository ;

	

	@Autowired
	public SessionFormationServiceImpl(SessionFormationRepository sessionRepository, programFormationRepository programRepository, ListProgrammeRepository programListRepository) {
		this.programRepository = programRepository;
		this.sessionRepository = sessionRepository;
		this.programListRepository = programListRepository;
	}



	@Override
	public SessionFormationDTO save(SessionFormationDTO dto ,Integer idProgram) {
		// TODO Auto-generated method stub
		programFormation program =programRepository.findById(idProgram).get();
		dto.setProgram(program);
		return SessionFormationDTO.fromEntity(sessionRepository.save(SessionFormationDTO.toEntity(dto)));
	}



	@Override
	public List<SessionFormationDTO> findAll() {
		// TODO Auto-generated method stub
		return sessionRepository.findAll().stream().map(SessionFormationDTO::fromEntity).collect(Collectors.toList());
	}



	@Override
	public SessionFormationDTO updateListProgrammme(Integer idSession, String programme) {
	    // Get the existing list of programmes
		Optional<SessionFormation> session =sessionRepository.findById(idSession);
		ListProgramme programmeList = new ListProgramme();

		programmeList.setElement(programme);
		programmeList.setSession(session.get());
	   
		programListRepository.save(programmeList);
	    // Set the updated list back to the DTO

	    // Return the updated DTO
	    return SessionFormationDTO.fromEntity(sessionRepository.save(session.get()));
	}



	@Override
	public List<ListProgrammeDTO> findBySession_Id(Integer sessionId) {
		// TODO Auto-generated method stub
		List<ListProgramme> listProgrammes =programListRepository.findBySession_Id(sessionId);
		return listProgrammes.stream()
                .map(ListProgrammeDTO::fromEntity)
                .collect(Collectors.toList()); 
	}



	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		 sessionRepository.deleteById(id);		
	}



	@Override
	public void deleteProgramme(Integer id) {
		// TODO Auto-generated method stub
		
		programListRepository.deleteById(id);
	}
}
