package com.example.imagemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileController {
    // 2. Xem file
    @GetMapping("api/v1/files/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        // Code logic
        return null;
    }

    // 3. Xoa file
    @DeleteMapping("api/v1/files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        // Code logic

        return null;
    }
}
