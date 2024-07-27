package com.pfe.Back.service;

import java.util.List;

import com.pfe.Back.DTO.ligneDTO;

public interface ligneService {
	ligneDTO save(ligneDTO dto);
	ligneDTO findById(Integer id);
	List<ligneDTO> findAll();
	void delete(Integer id );
}
