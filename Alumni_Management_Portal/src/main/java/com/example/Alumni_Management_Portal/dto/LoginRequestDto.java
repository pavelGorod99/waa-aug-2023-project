package com.example.Alumni_Management_Portal.dto;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}