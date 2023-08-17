package com.example.springbootfirstapp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Username không được để trống")
    @NotNull(message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @NotNull(message = "Password không được để trống")
    @Size(min = 8, max = 15, message = "Password phải có độ dài từ 8 đến 15 ký tự")
    private String password;

}

