package com.pfe.Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfe.Back.model.Token;
import com.pfe.Back.model.UserLigneAssignement;

public interface userLigneAssignementRepository extends JpaRepository<UserLigneAssignement, Integer> {

}
