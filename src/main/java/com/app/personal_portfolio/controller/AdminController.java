package com.app.personal_portfolio.controller;

import com.app.personal_portfolio.entity.User;
import com.app.personal_portfolio.security.CsrfTokenConfig;
import com.app.personal_portfolio.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute User user) {
        adminService.addUser(user);
        return "redirect:/signup";
    }

    @GetMapping("/signup")
    public String getSignup(@Validated @ModelAttribute User user) {
        return "signup";
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
