package com.example.springbootfirstapp.database;

import com.example.springbootfirstapp.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDB {

    public static List<User> users = new ArrayList<>(
            List.of(
                    new User(1, "user1", "user1@example.com", "password1", "avatar1"),
                    new User(2, "user2", "user2@example.com", "password2", "avatar2"),
                    new User(3, "user3", "user3@example.com", "password3", "avatar3"),
                    new User(4, "user4", "user4@example.com", "password4", "avatar4"),
                    new User(5, "user5", "user5@example.com", "password5", "avatar5"),
                    new User(6, "user6", "user6@example.com", "password6", "avatar6"),
                    new User(7, "user7", "user7@example.com", "password7", "avatar7"),
                    new User(8, "user8", "user8@example.com", "password8", "avatar8"),
                    new User(9, "user9", "user9@example.com", "password9", "avatar9"),
                    new User(10, "user10", "user10@example.com", "password10", "avatar10")
            )
    );

    public UserDB() {
    }

}
