package com.app.personal_portfolio.service;

import com.app.personal_portfolio.entity.User;
import com.app.personal_portfolio.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public User addUser(User user) {
        return adminRepository.save(user);
    }
}
