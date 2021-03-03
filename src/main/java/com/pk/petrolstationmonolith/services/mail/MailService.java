package com.pk.petrolstationmonolith.services.mail;

import com.pk.petrolstationmonolith.enums.ServiceType;
import com.pk.petrolstationmonolith.enums.AlarmType;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final Configuration emailConfig;

    public MailService(JavaMailSender mailSender, @Qualifier("emailConfigBean") Configuration emailConfig) {
        this.mailSender = mailSender;
        this.emailConfig = emailConfig;
    }

    public void sendPasswordResetMail(String email, UUID token) {
        Map<String, String> model = new HashMap<>();
        model.put("token", token.toString());

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("password_reset.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Reset Your Petrol Station Account Password");

            mailSender.send(message);
            log.trace("Password reset mail has been sent");
        } catch (IOException | MessagingException | TemplateException e) {
            log.error("Error during sending password reset mail. Message: " + e.getMessage());
        }

    }

    public void sendEmailConfirmationMail(String email, UUID token) {
        Map<String, String> model = new HashMap<>();
        model.put("link", "http://localhost:8080/account/registration/" + token);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("email_confirmation.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Active Your Petrol Station Account");

            mailSender.send(message);
            log.trace("Email confirmation mail has been sent");
        } catch (IOException | MessagingException | TemplateException e) {
            log.error("Error during sending email confirmation mail. Message: " + e.getMessage());
        }

    }

    public void sendEmailUpdateMail(String email, UUID token) {
        Map<String, String> model = new HashMap<>();
        model.put("token", token.toString());

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("email_update.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Update Your Petrol Station Account Email");

            mailSender.send(message);
            log.trace("Email update mail has been sent");
        } catch (IOException | MessagingException | TemplateException e) {
            log.error("Error during sending email update mail. Message: " + e.getMessage());
        }

    }

    public void sendMonitoringAlarmMail(String email, ServiceType serviceType, AlarmType alarmType, int tankNumber,
                                        int value, LocalDateTime dateTime) {
        Map<String, String> model = new HashMap<>();
        model.put("serviceType", serviceType.name());
        model.put("alarmType", alarmType.name());
        model.put("tankNumber", String.valueOf(tankNumber));
        model.put("value", value == -1 ? "" : String.valueOf(value));
        model.put("dataTime", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate("monitoring_alarm.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject("Monitoring alarm");

            mailSender.send(message);
            log.trace("Monitoring alarm mail has been sent");
        } catch (IOException | MessagingException | TemplateException e) {
            log.error("Error during sending monitoring alarm mail. Message: " + e.getMessage());
        }

    }

}
