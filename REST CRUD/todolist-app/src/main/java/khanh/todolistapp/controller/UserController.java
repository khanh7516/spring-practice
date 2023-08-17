package khanh.todolistapp.controller;

import khanh.todolistapp.InputData.LoginData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginData loginData){

    }


}
