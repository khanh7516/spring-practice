package com.example.springbootfirstapp.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BmiRequest {
    private double height;
    private double weight;
}
