package com.app.personal_portfolio.entity;

import jakarta.annotation.Nonnull;

public class Email {

    @Nonnull
    private String username;
	@Nonnull
    private String emailId;
	@Nonnull
    private String subject;
	@Nonnull
    private String message;
   
    public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
}
