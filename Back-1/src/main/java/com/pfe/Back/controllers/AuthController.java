package com.pfe.Back.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.DTO.auth.AuthenticationResponse;
import com.pfe.Back.DTO.auth.AuthenticationResponseWithUser;
import com.pfe.Back.DTO.auth.LoginDto;
import com.pfe.Back.DTO.auth.SignUpDto;
import com.pfe.Back.model.Token;
import com.pfe.Back.model.User;
import com.pfe.Back.repository.UserRepository;
import com.pfe.Back.service.impl.AuthenticationService;
import com.pfe.Back.service.impl.LogoutService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationService service;
  private final LogoutService logoutService ;
  private final UserRepository userRepository ;
  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody SignUpDto request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<Token> authenticate(
      @RequestBody LoginDto request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }
  @PostMapping("/logout")
  public void logout(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
	  logoutService.logout(request, response, null);
  }
 @GetMapping(value = "/allUsers",produces = "application/json")
public List<User> GetAllUsers() {
	 return userRepository.findAll(); 
	 }
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id")Integer id) {
		 service.deleteById(id);
	}

}
