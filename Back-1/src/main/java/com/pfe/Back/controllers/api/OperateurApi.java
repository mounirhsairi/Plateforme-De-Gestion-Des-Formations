package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Back.DTO.OperateurDTO;
import com.pfe.Back.DTO.OperateurOperationAssignmentDTO;
import com.pfe.Back.model.OperateurFromExcel;

import io.swagger.annotations.Api;
@CrossOrigin(origins="*")
@Api("/operateurs")
public interface OperateurApi {
	@PostMapping(value = "/operateurs/Create/operation/{idOperation}", consumes = "application/json", produces = "application/json")
	OperateurDTO save(@RequestBody OperateurDTO dto,@PathVariable("idOperation") Integer idOperation);
	
	@GetMapping(value = "/operateurs/{idOperateur}",produces = "application/json")
	OperateurDTO findById(@PathVariable("idOperateur")Integer id);
	
	@GetMapping(value = "/operateurs/mat/{matricule}",produces = "application/json")
	OperateurDTO findByMatriculeOperateur(@PathVariable("matricule")String matricule);

	@GetMapping(value = "/operateurs/ch/{Chaine}",produces = "application/json")
	List<OperateurDTO> findAllByChaine(@PathVariable("Chaine")String Chaine);
	
	@GetMapping(value = "/operateurs/all",produces = "application/json")
	List<OperateurDTO> findAll();
	
	@DeleteMapping(value = "/operateurs/delete/{idOperateur}")
	void delete(@PathVariable("idOperateur")Integer id );
	@GetMapping(value = "/operateurs/all1",produces = "application/json")

	List<OperateurOperationAssignmentDTO> findAll1();
	
	@GetMapping(value = "/operateurs/ligne/{idLigne}",produces = "application/json")

	List<OperateurDTO> findOperatorsByIdLigne(@PathVariable("idLigne")Integer idLigne);
	
	
	@PostMapping(value = "/operateurs/Create/{idDemande}", consumes = "application/json", produces = "application/json")
	List<OperateurFromExcel> save(@RequestBody List<OperateurFromExcel> dtos ,@PathVariable("idDemande") Integer idDemande);
	
	@PatchMapping(value ="/operateurs/update/etat/")
	List<OperateurDTO> updateEtat(@RequestBody List<OperateurDTO> dtos);
	
	@PatchMapping(value ="/operateurs/update/image/{idOperateur}")
	OperateurDTO updateImage(@RequestBody String image,@PathVariable("idOperateur") Integer idOperateur);
	
	@PatchMapping(value ="/operateurs/update/operation/{idOperateur}/{idOperation}")
	OperateurDTO updateOperation(@PathVariable("idOperation") Integer idOperation,@PathVariable("idOperateur") Integer idOperateur);

	
	@PatchMapping(value ="/operateurs/update/CodeFormation/{id}")
	OperateurOperationAssignmentDTO updateCodeFormation(@RequestBody String CodeFormation,@PathVariable("id") Integer id);

	@PatchMapping(value ="/operateurs/update/Methode/{id}")
	OperateurOperationAssignmentDTO updateMethodeSuivi(@RequestBody String CodeFormation,@PathVariable("id") Integer id);

	@PatchMapping(value ="/operateurs/update/Document/{id}")
	OperateurOperationAssignmentDTO updateDocumentIdentification(@RequestBody String CodeFormation,@PathVariable("id") Integer id);

	@PatchMapping(value ="/operateurs/update/qualification/{idOperateur}")
	OperateurDTO updateQualification(@RequestBody String qualification,@PathVariable("idOperateur") Integer idOperateur);
	
	@PatchMapping(value ="/operateurs/update/telephone/{idOperateur}")
	OperateurDTO updateTel(@RequestBody String telephone,@PathVariable("idOperateur") Integer idOperateur);

}
