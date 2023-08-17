package com.example.springbootfirstapp.service;

import com.example.springbootfirstapp.dao.UserDao;
import com.example.springbootfirstapp.model.User;
import com.example.springbootfirstapp.request.LoginRequest;
import com.example.springbootfirstapp.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserDao userDao;

    @Autowired
    public LoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    public LoginResponse login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        User user = userDao.findAll().stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user != null) {
            LoginResponse response = new LoginResponse();
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setAvatar(user.getAvatar());
            return response;
        } else {
            return null;
        }
    }
}
