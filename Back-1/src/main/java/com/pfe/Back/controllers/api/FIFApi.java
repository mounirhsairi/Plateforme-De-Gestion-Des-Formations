
package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pfe.Back.DTO.fifDTO;

import io.swagger.annotations.Api;

@Api("/files")
public interface FIFApi {

	@GetMapping(value = "/files/{operateurId}",produces = "application/json")

	List<fifDTO> findByOperateursId(@PathVariable("operateurId")Integer operateurId);
	
	@GetMapping(value = "/files/all",produces = "application/json")

	List<fifDTO> findAll();
	
	@PostMapping(value = "/files/Create", consumes = "application/json", produces = "application/json")
	fifDTO save(@RequestBody fifDTO dto);
	
	
	@GetMapping(value="files/upload/{matricule}")
	List<String> getPdfPaths(@PathVariable("matricule")String matricule);
}

