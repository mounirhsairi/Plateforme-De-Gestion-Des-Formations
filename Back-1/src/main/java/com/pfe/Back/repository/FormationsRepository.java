package com.pfe.Back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.Back.model.Formations;
import com.pfe.Back.model.Operateurs;

public interface FormationsRepository extends JpaRepository<Formations,Integer> {

	Optional<Formations> findById(Integer id);

}
