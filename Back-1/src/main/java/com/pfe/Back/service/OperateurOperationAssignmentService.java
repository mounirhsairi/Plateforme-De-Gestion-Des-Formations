package com.pfe.Back.service;

import java.util.List;

import com.pfe.Back.DTO.OperateurOperationAssignmentDTO;

public interface OperateurOperationAssignmentService {
	OperateurOperationAssignmentDTO save(OperateurOperationAssignmentDTO dto);
	OperateurOperationAssignmentDTO findById(Integer id);
	List<OperateurOperationAssignmentDTO> findAll();
	void delete(Integer id );
}
