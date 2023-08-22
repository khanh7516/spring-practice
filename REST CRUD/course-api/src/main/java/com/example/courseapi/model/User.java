package com.example.courseapi.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    public User() {
        System.out.print("User has been created!");
    }

    public void run() {
        System.out.print("User are running!!!");
    }
}
