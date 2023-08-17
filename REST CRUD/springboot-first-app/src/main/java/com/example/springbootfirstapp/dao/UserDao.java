package com.example.springbootfirstapp.dao;

import com.example.springbootfirstapp.database.UserDB;
import com.example.springbootfirstapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    public List<User> findAll() {
        return UserDB.users;
    }

    public void save(User user) {
        UserDB.users.add(user);
    }

}
