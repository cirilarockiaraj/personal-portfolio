package com.app.personal_portfolio.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import jakarta.annotation.Nonnull;

@Setter
@Getter
public class Email {
    @Id
    private String Id;
    @Nonnull
    private String username;
    private String emailId;
    @Nonnull
    private String subject;
	@Nonnull
    private String message;
}
