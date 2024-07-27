package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Back.DTO.opeartionDto;

import io.swagger.annotations.Api;

@Api("/operations")
public interface opeartionApi {
	@PostMapping(value = "/operations/Create/{idLigne}", consumes = "application/json", produces = "application/json")

	opeartionDto save(@RequestBody opeartionDto dto ,@PathVariable("idLigne")Integer idLigne);
	
	@GetMapping(value = "/operations/{idOperation}",produces = "application/json")
	opeartionDto findById(@PathVariable("idOperation")Integer id);
	
	@GetMapping(value = "/operations/lignes/{idLigne}",produces = "application/json")
	List<opeartionDto> findAllByLigneId(@PathVariable("idLigne")Integer id);
	
	@GetMapping(value = "/operations/all",produces = "application/json")
	List<opeartionDto> findAll();
	
	@DeleteMapping(value = "/operations/delete/{idOperation}")
	void delete(@PathVariable("idOperation")Integer id );
}
