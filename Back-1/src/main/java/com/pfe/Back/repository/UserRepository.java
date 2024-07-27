package com.pfe.Back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.pfe.Back.model.User;

public interface UserRepository extends JpaRepository <User ,Integer >{
    Optional<User> findByEmail(String email);

}
