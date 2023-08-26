package com.example.demothymeleaf.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private Integer id;
    private String name;
    private String email;
}
