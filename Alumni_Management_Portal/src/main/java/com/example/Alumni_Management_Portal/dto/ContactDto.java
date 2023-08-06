package com.example.Alumni_Management_Portal.dto;

import com.example.Alumni_Management_Portal.entities.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class ContactDto {
    private int id;
    private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String zip;
    private User user;
}