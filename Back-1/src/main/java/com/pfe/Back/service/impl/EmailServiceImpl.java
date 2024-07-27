package com.pfe.Back.service.impl;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.pfe.Back.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import lombok.extern.slf4j.Slf4j;
import sendgrid.EmailRequest;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    
    @Autowired
    private SendGrid sendGrid;
    
    @Override
    public Response sendEmail(EmailRequest emailreq) {
        Email fromEmail = new Email("Hsairi34@gmail.com");
        String subject = emailreq.getSubject();
        Email toEmail = new Email(emailreq.getTo());
        Content content = new Content("text/plain", emailreq.getBody());
        
        // Creating the Mail object
        Mail mail = new Mail(fromEmail, subject, toEmail, content);
        
        Request request = new Request();
        Response response = null;
        
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = this.sendGrid.api(request);
        } catch (Exception ex) {
            log.error("Error sending email: {}", ex.getMessage(), ex);
        }
        
        return response;
    }
    
    // Constructor, getters, setters
}
