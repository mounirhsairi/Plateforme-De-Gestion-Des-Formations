package com.pfe.Back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.Back.service.EmailService;
import com.sendgrid.Response;

import sendgrid.EmailRequest;

@RestController
@RequestMapping("/api/v1/auth")

public class EmailController {

	 @Autowired
	    private EmailService emailService;

	    @PostMapping("/sendemail")
	    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request ) {
	    	
	    	Response response = emailService.sendEmail(request);
	    	if(response.getStatusCode() == 200 || response.getStatusCode() == 202 ) {
	    		return new ResponseEntity<>("send successfuly", HttpStatus.OK);
	    	}
	        return new ResponseEntity<>("send successfuly", HttpStatus.NOT_FOUND);
	    }
}
