package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Back.DTO.FormationsDTO;

import io.swagger.annotations.Api;
@Api("/formations")
public interface FormationsApi {

	@PostMapping(value = "/formations/Create", consumes = "application/json", produces = "application/json")
	FormationsDTO save(@RequestBody FormationsDTO dto);
	
	@GetMapping(value = "/formations/all",produces = "application/json")

	List<FormationsDTO> findAll();
}
