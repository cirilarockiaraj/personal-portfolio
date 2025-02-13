package com.app.personal_portfolio.service;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.mail.username}")
    String from;
    @Value("${app.mail.to}")
    String to;
    @Value("${spring.mail.password}")
    String password;
    @Value("${spring.mail.port}")
    String port;
    @Value("${spring.mail.host}")
    String host;

    public AppData getJson() {
        return portfolio.findAll().get(0);
    }

    @Async
    public void emailSender(Email email) throws AddressException, MessagingException {
        // SMTP server settings
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        // Create email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getEmailId()));
        message.setSubject(email.getSubject());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(email.getMessage(), "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        // Send email
        Transport.send(message);
    }

    @Async
    public void saveMailContent(Email email) throws AddressException, MessagingException {
        emailRepo.save(email);
        logger.info("Email Request save successfully....");
        email.setEmailId(to);
        email.setMessage("<p>" + email.getMessage() + "</p>");
        emailSender(email);
        logger.info("Email successfully sent to admin...");
    }
}
