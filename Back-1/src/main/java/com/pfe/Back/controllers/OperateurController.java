package com.pfe.Back.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.OperateurDTO;
import com.pfe.Back.DTO.OperateurOperationAssignmentDTO;
import com.pfe.Back.controllers.api.OperateurApi;
import com.pfe.Back.model.OperateurFromExcel;
import com.pfe.Back.service.OperateurService;


@CrossOrigin(origins="*")
@RestController
public class OperateurController implements OperateurApi {
    
    private final OperateurService operateurService;
    
    @Autowired
    public OperateurController(OperateurService operateurService) {
        this.operateurService = operateurService;
    }

   

    @Override
    public OperateurDTO findById(Integer id) {
        return operateurService.findById(id);
    }

	@Override
	public OperateurDTO findByMatriculeOperateur(String matricule) {
		// TODO Auto-generated method stub
		return operateurService.findByMatriculeOperateur(matricule);
	}

	@Override
	public List<OperateurDTO> findAllByChaine(String Chaine) {
		// TODO Auto-generated method stub
		return operateurService.findAllByChaine(Chaine);
	}

	@Override
	public List<OperateurDTO> findAll() {
		// TODO Auto-generated method stub
		return operateurService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		operateurService.delete(id);
	}

	@Override
	public List<OperateurOperationAssignmentDTO> findAll1() {
		// TODO Auto-generated method stub
		return operateurService.findAll1();
	}



	@Override
	public OperateurDTO save(OperateurDTO dto, Integer idOperation) {
		// TODO Auto-generated method stub
		return operateurService.save(dto, idOperation);	}



	@Override
	public List<OperateurDTO> findOperatorsByIdLigne(Integer idLigne) {
		// TODO Auto-generated method stub
		return operateurService.findOperatorsByIdLigne(idLigne);
	}



	@Override
	public List<OperateurFromExcel> save(List<OperateurFromExcel> dtos ,Integer idDemande) {
		// TODO Auto-generated method stub
		return operateurService.save(dtos, idDemande);
	}



	@Override
	public List<OperateurDTO> updateEtat(List<OperateurDTO> dtos) {
		// TODO Auto-generated method stub
		return operateurService.updateEtat(dtos);
	}



	@Override
	public OperateurDTO updateImage(String image, Integer idOperateur) {
		// TODO Auto-generated method stub
		return operateurService.updateImage(image, idOperateur);
	}



	@Override
	public OperateurDTO updateOperation(Integer idOperation, Integer idOperateur) {
		// TODO Auto-generated method stub
		return operateurService.updateOperation(idOperation, idOperateur);
	}



	@Override
	public OperateurOperationAssignmentDTO updateCodeFormation(String CodeFormation, Integer id) {
		// TODO Auto-generated method stub
		return operateurService.updateCodeFormation(CodeFormation, id);
	}



	@Override
	public OperateurOperationAssignmentDTO updateMethodeSuivi(String CodeFormation, Integer id) {
		// TODO Auto-generated method stub
		return operateurService.updateMethodeSuivi(CodeFormation, id);
	}



	@Override
	public OperateurOperationAssignmentDTO updateDocumentIdentification(String CodeFormation, Integer id) {
		// TODO Auto-generated method stub
		return operateurService.updateDocumentIdentification(CodeFormation, id);
				
	}



	@Override
	public OperateurDTO updateQualification(String qualification, Integer idOperateur) {
		// TODO Auto-generated method stub
		return operateurService.updateQualification(qualification, idOperateur);
	}



	@Override
	public OperateurDTO updateTel(String telephone, Integer idOperateur) {
		// TODO Auto-generated method stub
		return operateurService.updateTel(telephone, idOperateur);
	}




}
