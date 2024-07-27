package com.pfe.Back.service;

import java.util.List;


import com.pfe.Back.DTO.FormationsDTO;
public interface FormationsService {

	FormationsDTO save(FormationsDTO dto);
	List<FormationsDTO> findAll();
}
