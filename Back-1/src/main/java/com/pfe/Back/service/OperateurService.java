package com.pfe.Back.service;

import java.util.List;

import com.pfe.Back.DTO.OperateurDTO;
import com.pfe.Back.DTO.OperateurOperationAssignmentDTO;
import com.pfe.Back.model.OperateurFromExcel;

public interface OperateurService {
	OperateurDTO save(OperateurDTO dto ,Integer idOperation);
	OperateurDTO findById(Integer id);
	OperateurDTO findByMatriculeOperateur(String matricule);
	List<OperateurDTO> findAllByChaine(String Chaine);
	List<OperateurDTO> findAll();
	List<OperateurDTO> findOperatorsByIdLigne(Integer idLigne);
	List<OperateurFromExcel> save(List<OperateurFromExcel> dto ,Integer idDemande);
	List<OperateurDTO> updateEtat(List<OperateurDTO> dto);
	OperateurDTO updateImage(String image ,Integer idOperateur);
	OperateurDTO updateTel(String telephone ,Integer idOperateur);

	OperateurDTO updateOperation(Integer idOperation ,Integer idOperateur);
	public List<OperateurOperationAssignmentDTO> findAll1();
	void delete(Integer id );
	OperateurOperationAssignmentDTO updateCodeFormation(String CodeFormation,Integer id);
	OperateurOperationAssignmentDTO updateMethodeSuivi(String CodeFormation,Integer id);
	OperateurOperationAssignmentDTO updateDocumentIdentification(String CodeFormation,Integer id);
	OperateurDTO updateQualification(String qualification ,Integer idOperateur);


	
}
