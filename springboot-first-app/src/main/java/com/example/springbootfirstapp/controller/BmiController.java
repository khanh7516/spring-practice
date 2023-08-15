package com.example.springbootfirstapp.controller;

import com.example.springbootfirstapp.model.BmiRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BmiController {

    @PostMapping("/bmi")
    public ResponseEntity<String> calculateBmiFromRequestBody(@RequestBody BmiRequest request) {
        double height = request.getHeight();
        double weight = request.getWeight();

        if (height <= 0 || weight <= 0) {
            return ResponseEntity.badRequest().body("height và weight phải lớn hơn 0");
        }

        double bmi = calculateBmi(height, weight);
        return ResponseEntity.ok("Chỉ số BMI của bạn: " + bmi);
    }

    private double calculateBmi(double height, double weight) {
        //tính chỉ số BMI
        return weight / (height * height);
    }
}
