package com.example.imagemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
    @GetMapping("/")
    public String getUserPage(Model model) {
        // Code logic
        return "index";
    }

    @GetMapping("/users/{id}/files")
    public String getFilesPage(Model model, @PathVariable Integer id) {
        // Code logic
        return "file";
    }

    //API
    @PostMapping("/api/user/{id}/files")
    public ResponseEntity<?> uploadFile(@PathVariable Integer id, @RequestParam("file")MultipartFile file) {
        return null;
    }
}
