package com.pfe.Back.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.Back.DTO.OperateurOperationAssignmentDTO;
import com.pfe.Back.DTO.ligneDTO;
import com.pfe.Back.model.Ligne;
import com.pfe.Back.model.OperateurOperationAssignment;
import com.pfe.Back.repository.OperateurOperationAssignmentRepository;
import com.pfe.Back.service.OperateurOperationAssignmentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OperateurOperationAssignmentServiceImpl implements OperateurOperationAssignmentService {

	private OperateurOperationAssignmentRepository operateurOperationAssignmentRepository;
	
	@Autowired
	public OperateurOperationAssignmentServiceImpl(
			OperateurOperationAssignmentRepository operateurOperationAssignmentRepository) {
		this.operateurOperationAssignmentRepository = operateurOperationAssignmentRepository;
	}

	@Override
	public OperateurOperationAssignmentDTO save(OperateurOperationAssignmentDTO dto) {
		// TODO Auto-generated method stub
		return OperateurOperationAssignmentDTO.fromEntity(operateurOperationAssignmentRepository.save(OperateurOperationAssignmentDTO.toEntity(dto)));
	}

	@Override
	public OperateurOperationAssignmentDTO findById(Integer id) {
		// TODO Auto-generated method stub
		if(id == null) {
			log.error("OperateurOperationAssignment id is null");
		}
		// TODO Auto-generated method stub
		Optional <OperateurOperationAssignment> OOA = operateurOperationAssignmentRepository.findById(id);
		return Optional.of(OperateurOperationAssignmentDTO.fromEntity(OOA.get())).orElseThrow() ;
	}

	@Override
	public List<OperateurOperationAssignmentDTO> findAll() {
		// TODO Auto-generated method stub
		return operateurOperationAssignmentRepository.findAll().stream().map(OperateurOperationAssignmentDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		operateurOperationAssignmentRepository.deleteById(id);
	}

	
	

}
