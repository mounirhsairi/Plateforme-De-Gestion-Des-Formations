package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Back.DTO.ligneDTO;

import io.swagger.annotations.Api;

@Api("/lignes")
public interface ligneApi {
	@PostMapping(value = "/lignes/Create", consumes = "application/json", produces = "application/json")

	ligneDTO save(@RequestBody ligneDTO dto);
	
	@GetMapping(value = "/lignes/{idLigne}",produces = "application/json")
	ligneDTO findById(@PathVariable("idLigne")Integer id);
	
	
	@GetMapping(value = "/lignes/all",produces = "application/json")
	List<ligneDTO> findAll();
	
	@DeleteMapping(value = "/lignes/delete/{idLigne}")
	void delete(@PathVariable("idLigne")Integer id );
}
