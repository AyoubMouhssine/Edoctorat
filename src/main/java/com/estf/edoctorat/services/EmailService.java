package com.estf.edoctorat.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendConfirmationEmail(String to, String confirmationUrl) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Email Confirmation");
            helper.setText("Please confirm your email by clicking the link below:\n" + confirmationUrl);
            javaMailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            e.printStackTrace();
        }
    }
}
