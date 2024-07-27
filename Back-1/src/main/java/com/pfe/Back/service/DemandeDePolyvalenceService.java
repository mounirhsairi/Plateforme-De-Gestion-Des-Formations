package com.pfe.Back.service;

import java.util.List; 

import com.pfe.Back.DTO.DemandeDePolyvalenceDTO;
import com.pfe.Back.DTO.DemandeDePolyvalenceDataDTO;
import com.pfe.Back.model.EtatDemandeDeFormation;

public interface DemandeDePolyvalenceService {
	
	DemandeDePolyvalenceDTO save(DemandeDePolyvalenceDTO dto);
	List<DemandeDePolyvalenceDTO> findAll();
	DemandeDePolyvalenceDTO findById(Integer id);
	DemandeDePolyvalenceDataDTO save(DemandeDePolyvalenceDataDTO dto);
	DemandeDePolyvalenceDTO updateEtatDemande(Integer idDemande ,EtatDemandeDeFormation etatDemande);
	void delete(Integer id );
	DemandeDePolyvalenceDTO justsave(DemandeDePolyvalenceDTO dto);
	DemandeDePolyvalenceDTO update(Integer demandeId);

}
