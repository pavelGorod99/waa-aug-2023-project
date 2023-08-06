package com.example.Alumni_Management_Portal.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
    private int id;
    private String city;
    private String state;
    private String zip;
}
