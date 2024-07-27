package com.pfe.Back.controllers.api;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Back.DTO.DemandeDeRecrutementDTO;
import com.pfe.Back.DTO.OperateurDTO;
import com.pfe.Back.model.EtatDemandeDeFormation;

import io.swagger.annotations.Api;
@Api("/demandes")
public interface DemandeDeRecrutementApi {

	
	@PostMapping(value = "/demandes/Create", consumes = "application/json", produces = "application/json")

	DemandeDeRecrutementDTO save(@RequestBody DemandeDeRecrutementDTO dto);
	
	@GetMapping(value = "/demandes/all",produces = "application/json")

	List<DemandeDeRecrutementDTO> findAll();
	
	@PatchMapping(value ="/demandes/update/etat/{idDemande}/{etatCommande}")
	DemandeDeRecrutementDTO updateEtatDemande(@PathVariable("idDemande")Integer idDemande,@PathVariable("etatCommande") EtatDemandeDeFormation etatDemande);
	
	@PatchMapping(value ="/demandes/update/lsitOperateur/{idDemande}")
	DemandeDeRecrutementDTO updateListOperateur(@PathVariable("idDemande")Integer idDemande,@RequestBody List<OperateurDTO> listOperateur);
	
	@GetMapping(value = "/demandes/{id}",produces = "application/json")
	DemandeDeRecrutementDTO findById(@PathVariable("id")Integer id);
	
	@DeleteMapping(value = "/demandes/delete/{id}")
	void delete(@PathVariable("id")Integer id );
	
	@PatchMapping(value ="/demandes/update/{idDemande}")
	public DemandeDeRecrutementDTO updateDemande(@PathVariable("idDemande")Integer idDemande,@RequestBody DemandeDeRecrutementDTO Dto);

	@PostMapping(value = "/demandes/just/Create", consumes = "application/json", produces = "application/json")

	DemandeDeRecrutementDTO justsave(@RequestBody DemandeDeRecrutementDTO dto);
	
	@PatchMapping(value ="/demandes/UEE/{idDemande}")
	public DemandeDeRecrutementDTO update(@PathVariable("idDemande")Integer demandeId);
}
