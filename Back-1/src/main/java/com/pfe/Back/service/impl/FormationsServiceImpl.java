package com.pfe.Back.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.Back.DTO.FormationsDTO;
import com.pfe.Back.repository.FormationsRepository;
import com.pfe.Back.service.FormationsService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FormationsServiceImpl implements FormationsService {

	@Autowired
	private FormationsRepository formationsRepository ;
	
	@Override
	public FormationsDTO save(FormationsDTO dto) {
		// TODO Auto-generated method stub
		return FormationsDTO.fromEntity(formationsRepository.save(FormationsDTO.toEntity(dto)));
	}

	@Override
	public List<FormationsDTO> findAll() {
		// TODO Auto-generated method stub
		
		return formationsRepository.findAll().stream().map(FormationsDTO::fromEntity).collect(Collectors.toList());
	}

}
