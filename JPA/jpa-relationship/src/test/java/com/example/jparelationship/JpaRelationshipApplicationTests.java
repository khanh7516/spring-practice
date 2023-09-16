package com.example.jparelationship;

import com.example.jparelationship.entity.Card;
import com.example.jparelationship.entity.User;
import com.example.jparelationship.repository.CardRepository;
import com.example.jparelationship.repository.UserRepository;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class JpaRelationshipApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;
    @Test
    void save_user_card() {
        Faker faker = new Faker();
        for (int i = 0; i < 10 ; i++) {
            Card card = new Card();
            card.setCode(faker.code().isbn10());
            cardRepository.save(card);

            User user = new User();
            user.setName(faker.name().fullName());
            user.setCard(card);
            userRepository.save(user);
        }
    }

    @Test
    void save_user_card_2() {
        Faker faker = new Faker();
        for (int i = 0; i < 5 ; i++) {
            User user = new User();
            user.setName(faker.name().fullName());
            user.setCard(new Card(null, faker.code().isbn10()));
            userRepository.save(user);
        }
    }

    @Test
    void get_user_by_id() {
        Optional<User> user = userRepository.findById(1);
        System.out.println(user.get().getName());
        System.out.println(user.get().getCard().getCode());
    }

    @Test
    void delete_user() {
        userRepository.deleteById(2);
    }

}
