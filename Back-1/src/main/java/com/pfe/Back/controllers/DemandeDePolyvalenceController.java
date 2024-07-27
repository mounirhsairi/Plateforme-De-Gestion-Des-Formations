package com.pfe.Back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.DemandeDePolyvalenceDTO;
import com.pfe.Back.DTO.DemandeDePolyvalenceDataDTO;
import com.pfe.Back.controllers.api.DemandeDePolyvalenceApi;
import com.pfe.Back.model.EtatDemandeDeFormation;
import com.pfe.Back.service.DemandeDePolyvalenceService;

@CrossOrigin(origins="*")

@RestController
public class DemandeDePolyvalenceController implements DemandeDePolyvalenceApi {

	
	@Autowired
	private DemandeDePolyvalenceService demandeService ;
	@Override
	public DemandeDePolyvalenceDTO save(DemandeDePolyvalenceDTO dto) {
		// TODO Auto-generated method stub
		return demandeService.save(dto);
	}
	@Override
	public List<DemandeDePolyvalenceDTO> findAll() {
		// TODO Auto-generated method stub
		return demandeService.findAll();
	}
	@Override
	public DemandeDePolyvalenceDataDTO save(DemandeDePolyvalenceDataDTO dto) {
		// TODO Auto-generated method stub
		return demandeService.save(dto);
	}
	@Override
	public DemandeDePolyvalenceDTO updateEtatDemande(Integer idDemande, EtatDemandeDeFormation etatDemande) {
		// TODO Auto-generated method stub
		return demandeService.updateEtatDemande(idDemande, etatDemande);
	}
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		demandeService.delete(id);
	}
	@Override
	public DemandeDePolyvalenceDTO findById(Integer id) {
		// TODO Auto-generated method stub
		return demandeService.findById(id);
	}
	@Override
	public DemandeDePolyvalenceDTO justsave(DemandeDePolyvalenceDTO dto) {
		// TODO Auto-generated method stub
		return demandeService.justsave(dto);
	}
	@Override
	public DemandeDePolyvalenceDTO update(Integer demandeId) {
		// TODO Auto-generated method stub
		return demandeService.update(demandeId);
	}

}
