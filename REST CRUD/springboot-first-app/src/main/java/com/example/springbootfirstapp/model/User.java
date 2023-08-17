package com.example.springbootfirstapp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String avatar;
}
