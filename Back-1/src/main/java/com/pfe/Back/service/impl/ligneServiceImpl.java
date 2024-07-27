package com.pfe.Back.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfe.Back.DTO.ligneDTO;
import com.pfe.Back.model.Ligne;
import com.pfe.Back.repository.ligneRepository;
import com.pfe.Back.service.ligneService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ligneServiceImpl implements ligneService {

	private final ligneRepository ligneR ;
	@Autowired
	public ligneServiceImpl(ligneRepository ligneR) {
		this.ligneR = ligneR;
	}

	@Override
	public ligneDTO save(ligneDTO dto) {
		// TODO Auto-generated method stub
		return ligneDTO.fromEntity(ligneR.save(ligneDTO.toEntity(dto)));
	}

	
	@Override
	public ligneDTO findById(Integer id) {
		if(id == null) {
			log.error("ligne id is null");
		}
		// TODO Auto-generated method stub
		Optional <Ligne> ligne = ligneR.findById(id);
		return Optional.of(ligneDTO.fromEntity(ligne.get())).orElseThrow() ;

	}

	@Override
	public List<ligneDTO> findAll() {
		// TODO Auto-generated method stub
		return ligneR.findAll().stream().map(ligneDTO::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		ligneR.deleteById(id);
	}

}
