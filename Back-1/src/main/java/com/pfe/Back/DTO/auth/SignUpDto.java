package com.pfe.Back.DTO.auth;

import java.util.List;

import com.pfe.Back.model.Ligne;
import com.pfe.Back.model.Roles;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
	private String name ;
	private String username ;
	private String email ;
	private String password ;
    private String image;
	private Roles roleName;
	private List<Integer> idLigne ;
   



}
