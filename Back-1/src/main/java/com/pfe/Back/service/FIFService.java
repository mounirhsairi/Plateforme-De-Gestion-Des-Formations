package com.pfe.Back.service;

import java.util.List;


import com.pfe.Back.DTO.fifDTO;

public interface FIFService {

	List<fifDTO> findByOperateursId(Integer operateurId);
	List<fifDTO> findAll();
	fifDTO save(fifDTO dto);
	

}
