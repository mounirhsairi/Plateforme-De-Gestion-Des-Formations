package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Back.DTO.SessionFormationDTO;
import com.pfe.Back.DTO.programFormationDTO;
import com.pfe.Back.model.SessionFormation;

import io.swagger.annotations.Api;

@Api("/program")
public interface programFormationApi {
	
	@PostMapping(value = "/program/Create", consumes = "application/json", produces = "application/json")
	programFormationDTO save(@RequestBody programFormationDTO dto);
	
	@GetMapping(value = "/program/all",produces = "application/json")
	List<programFormationDTO> findAll();
	@GetMapping(value = "/program/{id}",produces = "application/json")
	programFormationDTO findById(@PathVariable("id")Integer id);
}
