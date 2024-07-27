package com.pfe.Back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.Back.model.FIF;

public interface FIFRepository extends JpaRepository<FIF, Integer> {
	
    List<FIF> findByOperateursId(Integer idOperateurs);
}

