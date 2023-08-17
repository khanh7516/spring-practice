package com.example.springbootfirstapp.service;

import org.springframework.stereotype.Service;

@Service
public class BmiService {

    public double calculateBmi(double height, double weight) {
        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Chiều cao và cân nặng phải lớn hơn 0");
        }
        return weight / (height * height);
    }
}

