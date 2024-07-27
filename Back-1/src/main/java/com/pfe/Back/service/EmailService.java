package com.pfe.Back.service;

import com.sendgrid.Response;

import sendgrid.EmailRequest;

public interface EmailService {

	Response sendEmail(EmailRequest emailreq);
}
