package com.pfe.Back.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.Back.DTO.fifDTO;
import com.pfe.Back.repository.FIFRepository;
import com.pfe.Back.service.FIFService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FIFServiceImpl implements FIFService{
    @Autowired
    private FIFRepository fileRepository;

	@Override
	public List<fifDTO> findByOperateursId(Integer operateurId) {
		// TODO Auto-generated method stub
		return  fileRepository.findByOperateursId(operateurId).stream().map(fifDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public List<fifDTO> findAll() {
		// TODO Auto-generated method stub
		return fileRepository.findAll().stream().map(fifDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public fifDTO save(fifDTO dto) {
		// TODO Auto-generated method stub
		return fifDTO.fromEntity(fileRepository.save(fifDTO.toEntity(dto)));
	}

   
}