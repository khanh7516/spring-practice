package com.example.springbootfirstapp.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Random;

@RestController
@RequestMapping("/random-color/")
public class ColorController {

    @GetMapping("")
    public ResponseEntity<String> getRandomColor(@RequestParam("type") int type) {
        String color = generateRandomColor(type);
        if (color == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type không hợp lệ");
        }
        return ResponseEntity.ok(color);
    }

    private String generateRandomColor(int type) {
        Random random = new Random();
        switch (type) {
            case 1:
                return getRandomColorName();
            case 2:
                return getRandomHexColor(random);
            case 3:
                return getRandomRgbColor(random);
            default:
                return null;
        }
    }

    private String getRandomColorName() {
        String[] colorNames = {"black", "blue", "red", "green"};
        Random random = new Random();
        int index = random.nextInt(colorNames.length);
        return colorNames[index];
    }

    private String getRandomHexColor(Random random) {
        return String.format("#%02X%02X%02X", random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private String getRandomRgbColor(Random random) {
        return String.format("rgb(%d, %d, %d)", random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
