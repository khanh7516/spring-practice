package com.example.springbootfirstapp.controller;

import com.example.springbootfirstapp.request.BmiRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.springbootfirstapp.service.BmiService;


@RestController
@RequestMapping("/bmi")
@Validated
@CrossOrigin(origins = "*")
public class BmiController {

    @Autowired
    private BmiService bmiService;

    @GetMapping("")
    public ResponseEntity<?> getBmi(@RequestParam("height") double height, @RequestParam("weight") double weight) {
        double bmi = bmiService.calculateBmi(height, weight);
        return ResponseEntity.ok("Chỉ số BMI của bạn: " + bmi);
    }

    @PostMapping("")
    public ResponseEntity<?> postBmi(@RequestBody @Valid BmiRequest request) {
        double height = request.getHeight();
        double weight = request.getWeight();

        try {
            double bmi = bmiService.calculateBmi(height, weight);
            return ResponseEntity.ok("Chỉ số BMI của bạn: " + bmi);
        } catch (IllegalArgumentException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
