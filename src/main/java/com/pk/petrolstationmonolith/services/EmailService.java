package com.pk.petrolstationmonolith.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender mailSender;


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResetPasswordEmail(String email, UUID token) {
        String bodyMessage = "Click the link below to reset your password:";
        String link = "http://localhost:8080/account/password?token=" + token + "&email=" + email;
        String body = bodyMessage.concat(link);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Reset your Petrol station password");
        message.setText(body);
        mailSender.send(message);
    }


}
