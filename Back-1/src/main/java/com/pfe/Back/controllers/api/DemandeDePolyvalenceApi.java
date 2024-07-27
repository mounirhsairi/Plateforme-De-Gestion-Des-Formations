package com.pfe.Back.controllers.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.Back.DTO.DemandeDePolyvalenceDTO;
import com.pfe.Back.DTO.DemandeDePolyvalenceDataDTO;
import com.pfe.Back.model.EtatDemandeDeFormation;

import io.swagger.annotations.Api;

@Api("/demandesPoly")
public interface DemandeDePolyvalenceApi {

	@PostMapping(value = "/demandesPoly/Create", consumes = "application/json", produces = "application/json")
	DemandeDePolyvalenceDTO save(@RequestBody DemandeDePolyvalenceDTO dto);
	
	@GetMapping(value = "/demandesPoly/all",produces = "application/json")

	 List<DemandeDePolyvalenceDTO> findAll();
	
	
	@PostMapping(value = "/demandesDataPoly/Create", consumes = "application/json", produces = "application/json")
	DemandeDePolyvalenceDataDTO save(@RequestBody DemandeDePolyvalenceDataDTO dto);
	
	@PatchMapping(value ="/demandesPoly/update/etat/{idDemande}/{etatCommande}")
	DemandeDePolyvalenceDTO updateEtatDemande(@PathVariable("idDemande")Integer idDemande,@PathVariable("etatCommande") EtatDemandeDeFormation etatDemande);
	
	@DeleteMapping(value = "/demandesPoly/delete/{id}")
	void delete(@PathVariable("id")Integer id );
	
	@GetMapping(value = "/demandesPoly/{id}",produces = "application/json")
	DemandeDePolyvalenceDTO findById(@PathVariable("id")Integer id);
	
	@PostMapping(value = "/demandesPoly/just/Create", consumes = "application/json", produces = "application/json")

	public DemandeDePolyvalenceDTO justsave(DemandeDePolyvalenceDTO dto);
	
	@PatchMapping(value ="/demandesPoly/UEE/{idDemande}")
	DemandeDePolyvalenceDTO update(@PathVariable("idDemande")Integer demandeId);

}
