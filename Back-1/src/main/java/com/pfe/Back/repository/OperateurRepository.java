package com.pfe.Back.repository;
import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.Back.model.Operateurs;

public interface OperateurRepository extends JpaRepository<Operateurs, Integer> {
	Optional<Operateurs> findById(Integer id);
	Optional<Operateurs> findByMatricule(String Matricule);
	List<Operateurs> findAllByChaine(String Chaine);
}
