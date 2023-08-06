package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String zip;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
