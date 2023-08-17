package com.example.springbootfirstapp.controller;

import com.example.springbootfirstapp.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/random-color")
@CrossOrigin(origins = "*")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("")
    public ResponseEntity<String> getRandomColor(@RequestParam("type") int type) {
        String color = colorService.getRandomColor(type);
        if (color == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type không hợp lệ");
        }
        return ResponseEntity.ok(color);
    }
}
