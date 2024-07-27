package com.pfe.Back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.Back.model.ListProgramme;

public interface ListProgrammeRepository extends JpaRepository <ListProgramme, Integer> {

	
    List<ListProgramme> findBySession_Id(Integer sessionId);

}

