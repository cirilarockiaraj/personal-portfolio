package com.app.personal_portfolio.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;

public class CsrfTokenConfig {

    private final CsrfTokenRepository csrfTokenRepository;
    public CsrfTokenConfig(CsrfTokenRepository csrfTokenRepository) {
        this.csrfTokenRepository = csrfTokenRepository;
    }

    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) this.csrfTokenRepository.generateToken(request);
    }
}
