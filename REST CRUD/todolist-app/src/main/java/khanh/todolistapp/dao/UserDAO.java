package khanh.todolistapp.dao;

import khanh.signinapp.database.UserDB;
import khanh.signinapp.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAO {
    public List<User> findAll() {
        return UserDB.userList;
    }
}
