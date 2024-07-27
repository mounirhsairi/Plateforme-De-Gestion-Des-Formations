package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Back.DTO.ListProgrammeDTO;
import com.pfe.Back.DTO.SessionFormationDTO;
import com.pfe.Back.model.ListProgramme;

import io.swagger.annotations.Api;
@Api("/session")
public interface SessionFormationApi {
	
	@PostMapping(value = "/session/{idProgram}/Create", consumes = "application/json", produces = "application/json")

	SessionFormationDTO save(@RequestBody SessionFormationDTO dto,@PathVariable("idProgram")Integer idProgram);
	
	@GetMapping(value = "/session/all",produces = "application/json")

	List<SessionFormationDTO> findAll();
	
	@PatchMapping(value ="/session/update/{idSession}")
	SessionFormationDTO updateListProgrammme(@PathVariable("idSession")Integer idSession, @RequestBody String programme);

	
	@GetMapping(value = "/session/{sessionId}",produces = "application/json")

	public List<ListProgrammeDTO> findBySession_Id(@PathVariable("sessionId")Integer sessionId);
	
	@DeleteMapping(value = "/session/delete/{sessionId}")

	 void delete(@PathVariable("sessionId")Integer sessionId);
	
	@DeleteMapping(value = "/session/delete/programme/{id}")

	void deleteProgramme(@PathVariable("id")Integer id);

}
