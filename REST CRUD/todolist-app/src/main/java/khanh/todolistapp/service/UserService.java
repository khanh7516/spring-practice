package khanh.todolistapp.service;
import khanh.signinapp.model.User;
import khanh.todolistapp.InputData.LoginData;
import khanh.todolistapp.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> getAllPost() {
        return userDAO.findAll();
    }

    public Map<String, String> validate(LoginData loginData) {

    }

    


}
