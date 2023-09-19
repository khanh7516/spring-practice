package com.example.imagemanagement;

import com.example.imagemanagement.entity.User;
import com.example.imagemanagement.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageManagementApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUsersTest() {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .build();

            userRepository.save(user);
        }
    }

}
