package com.pk.petrolstationmonolith.services.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    private final Configuration emailConfig;

    public MailService(JavaMailSender mailSender, @Qualifier("emailConfigBean") Configuration emailConfig) {
        this.mailSender = mailSender;
        this.emailConfig = emailConfig;
    }

    public void sendPasswordResetEmail(String email, UUID token) {

        Map<String, String> model = new HashMap<>();
        model.put("link", "http://localhost:8080/account/password?token=" + token + "&email=" + email);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("password_reset.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Reset Your Petrol Station Account Password");

            mailSender.send(message);
        } catch (IOException | MessagingException | TemplateException e) {
            e.printStackTrace();
        }

    }


}
