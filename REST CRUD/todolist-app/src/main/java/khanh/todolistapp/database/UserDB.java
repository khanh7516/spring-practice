package khanh.signinapp.database;

import khanh.signinapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {

    public  static List<User> userList = new ArrayList<>(List.of(
            new User(1, "Khanh", "khanh@gmail.com", "1234", "avatar1")
    ));
}
