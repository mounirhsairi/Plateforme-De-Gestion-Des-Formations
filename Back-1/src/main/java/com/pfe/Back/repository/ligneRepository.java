package com.pfe.Back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.Back.model.Ligne;

public interface ligneRepository extends JpaRepository <Ligne, Integer> {
	Optional<Ligne> findByNomLigne(String nomLigne);
}
