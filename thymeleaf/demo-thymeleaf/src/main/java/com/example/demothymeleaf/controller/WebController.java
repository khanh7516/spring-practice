package com.example.demothymeleaf.controller;


import com.example.demothymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private List<User> userList = new ArrayList<>();

    public WebController() {
        this.userList = List.of(
                new User(1, "A", "A@.com"),
                new User(2, "B", "B@.com"),
                new User(3, "C", "C@.com"),
                new User(4, "D", "D@.com")
        );
    }

    @GetMapping("/")
    public String getHome(Model model) {
        String name = "K";

        model.addAttribute("name", "K");
        model.addAttribute("user", userList.get(0));
        model.addAttribute("userList", userList);
        return "index";
    }

//    @GetMapping("/admin")
//    public String getAdminPage() {
//        return "admin/admin";
//    }
}
