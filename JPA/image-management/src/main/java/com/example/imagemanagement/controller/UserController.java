package com.example.imagemanagement.controller;

import com.example.imagemanagement.entity.FileServer;
import com.example.imagemanagement.entity.User;
import com.example.imagemanagement.service.FileService;
import com.example.imagemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String getUserPage(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/users/{id}/files")
    public String getFilesPage(Model model, @PathVariable Integer id) {
        List<FileServer> files = fileService.getFilesByUserId(id);
        model.addAttribute("files", files);
        return "file";
    }

    //API
    @PostMapping("/api/user/{id}/files")
    public ResponseEntity<?> uploadFile(@PathVariable Integer id, @RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok(fileService.uploadFile(id, file));
    }
}
