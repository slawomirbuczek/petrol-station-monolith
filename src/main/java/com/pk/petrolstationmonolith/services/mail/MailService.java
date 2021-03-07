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

        sendEmail(model, email, "password_reset.ftl", "Reset Your Petrol Station Account Password");
    }

    public void sendEmailConfirmationMail(String email, UUID token) {
        Map<String, String> model = new HashMap<>();
        model.put("link", "http://localhost:8080/account/registration/" + token);

        sendEmail(model, email, "email_confirmation.ftl", "Active Your Petrol Station Account");
    }

    public void sendEmailUpdateMail(String email, UUID token) {
        Map<String, String> model = new HashMap<>();
        model.put("token", token.toString());

        sendEmail(model, email, "email_update.ftl", "Update Your Petrol Station Account Email");
    }

    public void sendMonitoringAlarmMail(String email, ServiceType serviceType, AlarmType alarmType, int tankNumber,
                                        float value, LocalDateTime dateTime) {

        Map<String, String> model = new HashMap<>();
        model.put("serviceType", serviceType.name());
        model.put("alarmType", alarmType.name());
        model.put("tankNumber", String.valueOf(tankNumber));
        model.put("value", value < 0 ? "" : String.valueOf(value));
        model.put("dataTime", dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        sendEmail(model, email, "monitoring_alarm.ftl", "Monitoring alarm");
    }

    private void sendEmail(Map<String, String> model, String email, String templateFile, String subject) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Template template = emailConfig.getTemplate(templateFile);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(html, true);
            mimeMessageHelper.setSubject(subject);

            mailSender.send(message);
            log.trace("Mail has been sent");
        } catch (IOException | MessagingException | TemplateException e) {
            log.error("Error during sending mail. Message: " + e.getMessage());
        }
    }

}
