package com.example.Alumni_Management_Portal.dto;

import com.example.Alumni_Management_Portal.entities.Address;
import com.example.Alumni_Management_Portal.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JobDto {
    private int id;
    private String company;
    private String title;
    private String description;
    private Address location;
    private String type;
    private User jobCreator;
    private List<User> applicants;
}
