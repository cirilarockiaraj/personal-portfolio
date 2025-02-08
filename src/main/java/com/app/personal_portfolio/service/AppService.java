package com.app.personal_portfolio.service;

import java.io.File;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.personal_portfolio.entity.AppData;
import com.app.personal_portfolio.entity.Email;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    @Value("${app.json.url}")
    private String appFileUrl;

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
        ObjectMapper mapper = new ObjectMapper();
        AppData specificData = null;
        try {
            File file = new File(appFileUrl);
            JsonNode res = mapper.readTree(file);
            specificData = mapper.convertValue(res, new TypeReference<AppData>() {
            });
            logger.info(appFileUrl, specificData);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return specificData;
    }

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
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to));
        message.setSubject(email.getSubject());
        String htmlContent = "<p>Hi, </p>"+email.getUsername()+"<br/><p>Thankyou for contact with me!</p>";
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(htmlContent, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        // Send email
        Transport.send(message);
        System.out.println("Email sent successfully!");
    }
}
