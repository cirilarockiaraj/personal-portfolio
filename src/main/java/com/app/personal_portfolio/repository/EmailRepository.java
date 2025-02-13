package com.app.personal_portfolio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.app.personal_portfolio.entity.Email;

public interface EmailRepository extends MongoRepository<Email, String> {
}
