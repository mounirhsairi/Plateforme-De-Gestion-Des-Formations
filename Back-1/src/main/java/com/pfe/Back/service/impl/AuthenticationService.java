package com.pfe.Back.service.impl;

import java.time.Instant; 

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pfe.Back.DTO.auth.AuthenticationResponseWithUser;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import com.pfe.Back.DTO.auth.AuthenticationResponse;
import com.pfe.Back.DTO.auth.LoginDto;
import com.pfe.Back.DTO.auth.SignUpDto;
import com.pfe.Back.model.Token;
import com.pfe.Back.model.Ligne;
import com.pfe.Back.model.UserLigneAssignement;

import com.pfe.Back.model.TokenType;
import com.pfe.Back.model.User;
import com.pfe.Back.repository.TokenRepository;
import com.pfe.Back.repository.UserRepository;
import com.pfe.Back.repository.ligneRepository;

import com.pfe.Back.repository.userLigneAssignementRepository;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;
	  private final TokenRepository tokenRepository;
	  private final PasswordEncoder passwordEncoder;
	  private final userLigneAssignementRepository ULAR ;
	  private final JwtService jwtService;
	  private final AuthenticationManager authenticationManager;
	  private final ligneRepository ligneRepository ;
	  private final userLigneAssignementRepository userLigneAssignementRepository;
	  public AuthenticationResponse register(SignUpDto request) {
		    
		    var user = User.builder()
		        .name(request.getName())
		        .username(request.getUsername())
		        .email(request.getEmail())
		        .Role(request.getRoleName())
		        .password(passwordEncoder.encode(request.getPassword()))
		        .image(request.getImage())
		        .build();
		    var savedUser = repository.save(user);
		    
		    List<UserLigneAssignement> userLigneAssignments = new ArrayList<>();
		    for (Integer ligneId : request.getIdLigne()) {
		        Optional<Ligne> ligne = ligneRepository.findById(ligneId);
		        if (ligne.isPresent()) {
		            UserLigneAssignement userLigneAssignement = new UserLigneAssignement();
		            userLigneAssignement.setUser(user);
		            userLigneAssignement.setLigne(ligne.get());
		            userLigneAssignments.add(userLigneAssignement);
		        } else {
		            // Handle the case when ligne is not found
		        }
		    }
		    
		    // Save all UserLigneAssignements
		    userLigneAssignementRepository.saveAll(userLigneAssignments);
		    
		    var jwtToken = jwtService.generateToken(user);
		    var refreshToken = jwtService.generateRefreshToken(user);
		    saveUserToken(savedUser, jwtToken);
		    
		    return AuthenticationResponse.builder()
		        .accessToken(jwtToken)
		        .refreshToken(refreshToken)
		        .build();
		}

	  

	  public Token authenticate(LoginDto request) {
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.getEmail(),
	            request.getPassword()
	        )
	    );
	    var user = repository.findByEmail(request.getEmail())
	        .orElseThrow();
	    var jwtToken = jwtService.generateToken(user);
	    var refreshToken = jwtService.generateRefreshToken(user);
	    revokeAllUserTokens(user);
	    saveUserToken(user, jwtToken);
	    return  Token.builder()
		        .user(user)
		        .token(jwtToken)
		        .tokenType(TokenType.BEARER)
		        .expired(false)
		        .revoked(false)
		        .build();
	  }

	  private void saveUserToken(User user, String jwtToken) {
	    var token = Token.builder()
	        .user(user)
	        .token(jwtToken)
	        .tokenType(TokenType.BEARER)
	        .expired(false)
	        .revoked(false)
	        .build();
	    tokenRepository.save(token);
	  }

	  private void revokeAllUserTokens(User user) {
	    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
	    if (validUserTokens.isEmpty())
	      return;
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	    });
	    tokenRepository.saveAll(validUserTokens);
	  }
	 public void deleteById(Integer id) {
		 var user = repository.findById(id)
			        .orElseThrow();
		 var assignement = user.getUserLigneAssignement();
		 ULAR.deleteAll(assignement);
		    var validUserTokens = tokenRepository.findAllByUserId(user.getId());
		    tokenRepository.deleteAll(validUserTokens);;
		 repository.deleteById(id);
	 }
	  public void refreshToken(
	          HttpServletRequest request,
	          HttpServletResponse response
	  ) throws IOException, StreamWriteException, DatabindException, java.io.IOException {
	    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    final String refreshToken;
	    final String userEmail;
	    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
	      return;
	    }
	    refreshToken = authHeader.substring(7);
	    userEmail = jwtService.extractUsername(refreshToken);
	    if (userEmail != null) {
	      var user = this.repository.findByEmail(userEmail)
	              .orElseThrow();
	      if (jwtService.isTokenValid(refreshToken, user)) {
	        var accessToken = jwtService.generateToken(user);
	        revokeAllUserTokens(user);
	        saveUserToken(user, accessToken);
	        var authResponse = AuthenticationResponse.builder()
	                .accessToken(accessToken)
	                .refreshToken(refreshToken)
	                .build();
	        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
	      }
	    }
	  }
	  
	  
}
