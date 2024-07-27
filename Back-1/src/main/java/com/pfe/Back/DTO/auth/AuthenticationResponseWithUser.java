package com.pfe.Back.DTO.auth;

import com.pfe.Back.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseWithUser {

	 private final AuthenticationResponse authenticationResponse;
	 private final User user;
}
