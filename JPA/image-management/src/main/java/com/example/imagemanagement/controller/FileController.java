package com.example.imagemanagement.controller;

import com.example.imagemanagement.entity.FileServer;
import com.example.imagemanagement.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/files")
public class FileController {
    @Autowired
    private FileService fileService;

    // 2. Xem file
    @GetMapping("/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        FileServer fileServer = fileService.getFileById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileServer.getType()))
                .body(fileServer.getData());
    }

    // 3. Xoa file
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        fileService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
