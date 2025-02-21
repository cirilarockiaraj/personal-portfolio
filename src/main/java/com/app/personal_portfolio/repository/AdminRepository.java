package com.app.personal_portfolio.repository;

import com.app.personal_portfolio.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByEmail(String email);
}
