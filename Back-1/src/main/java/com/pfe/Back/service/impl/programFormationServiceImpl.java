package com.pfe.Back.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.Back.DTO.SessionFormationDTO;
import com.pfe.Back.DTO.programFormationDTO;
import com.pfe.Back.model.SessionFormation;
import com.pfe.Back.model.programFormation;
import com.pfe.Back.repository.SessionFormationRepository;
import com.pfe.Back.repository.programFormationRepository;
import com.pfe.Back.service.programFormationService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class programFormationServiceImpl implements programFormationService {

	
	private final programFormationRepository programRepository ;

	private final SessionFormationRepository sessionRepository ;


	@Autowired
	public programFormationServiceImpl(programFormationRepository programRepository, SessionFormationRepository sessionRepository) {
		this.programRepository = programRepository;
		this.sessionRepository = sessionRepository;
	}
	
	
	
	@Override
	public programFormationDTO save(programFormationDTO dto) {
		
		programFormation savedprogram =programRepository.save(programFormationDTO.toEntity(dto));
		List<SessionFormationDTO> sessionDTO =dto.getSessionFormation();
		for(SessionFormationDTO session :sessionDTO) {
			session.setProgram(savedprogram);
			sessionRepository.save(SessionFormationDTO.toEntity(session));
		}
	    // Convert the saved program entity back to DTO and return
	    return programFormationDTO.fromEntity(savedprogram);
	}




	@Override
	public List<programFormationDTO> findAll() {
		// TODO Auto-generated method stub
		return programRepository.findAll().stream().map(programFormationDTO::fromEntity).collect(Collectors.toList());
	}



	@Override
	public programFormationDTO findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.of(programFormationDTO.fromEntity(programRepository.findById(id).get())).orElseThrow() ;
	}

}
