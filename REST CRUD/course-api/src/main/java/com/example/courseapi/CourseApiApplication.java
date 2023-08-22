package com.example.courseapi;

import com.example.courseapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseApiApplication implements CommandLineRunner {
//    @Autowired
    private User user;
    CourseApiApplication(User user) {
        this.user = user;
    }

    public static void main(String[] args) {

        SpringApplication.run(CourseApiApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
//        user.run();
//        User user = new User();
        user.run();
    }
}
