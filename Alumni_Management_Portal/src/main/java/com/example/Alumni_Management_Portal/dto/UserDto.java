package com.example.Alumni_Management_Portal.dto;
import com.example.Alumni_Management_Portal.entities.Almuni;
import com.example.Alumni_Management_Portal.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean isActive = false;
    private Boolean isDeleted = false;
    private String username;
//    private Role role;
    private Almuni almuni;
}
