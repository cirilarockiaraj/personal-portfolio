package com.app.personal_portfolio.service;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.personal_portfolio.entity.AppData;
import com.app.personal_portfolio.entity.Email;
import com.app.personal_portfolio.repository.EmailRepository;
import com.app.personal_portfolio.repository.PortfolioRepository;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class AppService {
    Logger logger = LogManager.getLogger(AppService.class.getName());

    @Autowired
    private PortfolioRepository portfolio;

    @Autowired
    private EmailRepository emailRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    String from;

    @Value("${app.mail.to}")
    String to;

    public AppData getJson() {
        return portfolio.findAll().get(0);
    }

    @Async
    public void emailSender(Email email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(email.getEmailId());
            helper.setSubject(email.getSubject());
            helper.setText(email.getMessage(), true);
            helper.setFrom(from);

            mailSender.send(message);
            logger.info("Email sent to {}", email.getEmailId());

        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
    }

    @Async
    public void saveMailContent(Email email) throws AddressException, MessagingException {
        emailRepo.save(email);
        logger.info("Email Request save successfully....");
        Email adminMailContent = new Email();
        adminMailContent.setSubject(email.getSubject());
        adminMailContent.setUsername(email.getUsername());
        adminMailContent.setEmailId(to);
        adminMailContent.setMessage("<p>" + email.getMessage() + "</p>");
        emailSender(adminMailContent);
    }
}
