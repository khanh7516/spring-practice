package com.example.imagemanagement.service;

import com.example.imagemanagement.entity.User;
import com.example.imagemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getUsers() {
        return userRepository.findAll();
    }






}
