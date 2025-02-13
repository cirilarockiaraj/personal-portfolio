package com.app.personal_portfolio.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.personal_portfolio.entity.AppData;
import com.app.personal_portfolio.entity.Email;
import com.app.personal_portfolio.service.AppService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PortfolioController {
    Logger logger = LogManager.getLogger(AppService.class.getName());
    AppData responseData;

    private final AppService appService;

    public PortfolioController(AppService emailService) {
        this.appService = emailService;
        responseData = emailService.getJson();
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("appData", responseData);
        logger.info("App rendering successfully");
        return "index";
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@Validated @RequestBody Email emailRequest) {
        try {
            Email emailCopy = getEmail(emailRequest);
            appService.emailSender(emailCopy);
            appService.saveMailContent(emailRequest);
            logger.info("Email sent successfully...");
            return ResponseEntity.ok().body("Email sent successfully..");
        } catch (Exception e) {
            logger.error(e);
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
                    .body("Error sending email: " + e.getMessage());
        }
    }

    private static @NotNull Email getEmail(Email emailRequest) {
        Email emailCopy = new Email();
        String htmlContent = "<p>Hi, " + emailRequest.getUsername()
                + "</p><br/><p>Your message recieved me. I will replay to you soon.</p><br/><br/><i><b>Note: </b>This is an automation mail. don't replay this mail. once I got your mail I will try to replay to you soon. thankyou for understanding.</i>";
        emailCopy.setUsername(emailRequest.getUsername());
        emailCopy.setSubject("Replay: " + emailRequest.getSubject());
        emailCopy.setMessage(htmlContent);
        emailCopy.setEmailId(emailRequest.getEmailId());
        return emailCopy;
    }
}
