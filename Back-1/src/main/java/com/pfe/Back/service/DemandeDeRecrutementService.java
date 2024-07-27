package com.pfe.Back.service;

import java.util.List;


import com.pfe.Back.DTO.DemandeDeRecrutementDTO;
import com.pfe.Back.DTO.OperateurDTO;
import com.pfe.Back.model.EtatDemandeDeFormation;

public interface DemandeDeRecrutementService {
	DemandeDeRecrutementDTO save(DemandeDeRecrutementDTO dto);
	List<DemandeDeRecrutementDTO> findAll();
	DemandeDeRecrutementDTO updateEtatDemande(Integer idDemande ,EtatDemandeDeFormation etatDemande);
	DemandeDeRecrutementDTO updateListOperateur(Integer idDemande ,List<OperateurDTO> listOperateur);
	 DemandeDeRecrutementDTO findById(Integer id);
		void delete(Integer id );
	DemandeDeRecrutementDTO updateDemande(Integer idDemande, DemandeDeRecrutementDTO Dto);
	DemandeDeRecrutementDTO justsave(DemandeDeRecrutementDTO dto);
	public DemandeDeRecrutementDTO update(Integer demandeId) ;


}
