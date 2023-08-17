package com.example.springbootfirstapp.request;

import jakarta.validation.constraints.NotNull;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BmiRequest {
    @NotNull(message = "Chiều cao không được để trống")
    private double height;
    @NotNull(message = "Cân nặng không được để trống")
    private double weight;

}
