package com.pfe.Back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.Back.model.Operation;

public interface operationRepository extends JpaRepository <Operation, Integer> {
    List<Operation> findAllByLigneId(Integer ligneId);

}
