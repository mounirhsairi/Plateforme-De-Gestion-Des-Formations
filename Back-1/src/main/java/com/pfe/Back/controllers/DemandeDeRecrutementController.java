package com.pfe.Back.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.DemandeDeRecrutementDTO;
import com.pfe.Back.DTO.OperateurDTO;
import com.pfe.Back.controllers.api.DemandeDeRecrutementApi;
import com.pfe.Back.model.DemandeDeRecrutement;
import com.pfe.Back.model.EtatDemandeDeFormation;
import com.pfe.Back.service.DemandeDeRecrutementService;
@CrossOrigin(origins="*")

@RestController
public class DemandeDeRecrutementController implements DemandeDeRecrutementApi {
	@Autowired
	private DemandeDeRecrutementService demandeService ;

	@Override
	public DemandeDeRecrutementDTO save(DemandeDeRecrutementDTO dto) {
		// TODO Auto-generated method stub
		return demandeService.save(dto);
	}

	@Override
	public List<DemandeDeRecrutementDTO> findAll() {
		// TODO Auto-generated method stub
		return demandeService.findAll();
	}

	@Override
	public DemandeDeRecrutementDTO updateEtatDemande(Integer idDemande, EtatDemandeDeFormation etatDemande) {
		// TODO Auto-generated method stub
		return demandeService.updateEtatDemande(idDemande, etatDemande);
	}

	@Override
	public DemandeDeRecrutementDTO updateListOperateur(Integer idDemande, List<OperateurDTO> listOperateur) {
		// TODO Auto-generated method stub
		return demandeService.updateListOperateur(idDemande, listOperateur);
	}

	@Override
	public DemandeDeRecrutementDTO findById(Integer id) {
		// TODO Auto-generated method stub
		return demandeService.findById(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		demandeService.delete(id);
		
	}

	@Override
	public DemandeDeRecrutementDTO updateDemande(Integer idDemande, DemandeDeRecrutementDTO Dto) {
		// TODO Auto-generated method stub
		return demandeService.updateDemande(idDemande, Dto);
	}

	@Override
	public DemandeDeRecrutementDTO justsave(DemandeDeRecrutementDTO dto) {
		// TODO Auto-generated method stub
		return demandeService.justsave(dto);
	}

	@Override
	public DemandeDeRecrutementDTO update(Integer demandeId) {
		// TODO Auto-generated method stub
		return demandeService.update(demandeId);
	}
	
	
	
}
