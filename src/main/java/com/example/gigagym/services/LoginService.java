package com.example.gigagym.services;

import com.example.gigagym.models.Staff;
import com.example.gigagym.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean authenticate(String emailAddress, String password) {
        Staff user = loginRepository.findByEmailAddress(emailAddress);
        return user != null && user.getPassword().equals(password);
    }
}
