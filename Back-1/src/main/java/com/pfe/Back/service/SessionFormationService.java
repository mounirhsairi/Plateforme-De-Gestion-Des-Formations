package com.pfe.Back.service;

import java.util.List;

import com.pfe.Back.DTO.ListProgrammeDTO;
import com.pfe.Back.DTO.SessionFormationDTO;
import com.pfe.Back.model.ListProgramme;

public interface SessionFormationService {

	SessionFormationDTO save(SessionFormationDTO dto , Integer idProgram);
	SessionFormationDTO updateListProgrammme(Integer idSession ,String programme);
	List<SessionFormationDTO> findAll();
	List<ListProgrammeDTO> findBySession_Id(Integer sessionId);
	void delete(Integer id);
	void deleteProgramme(Integer id);


}
