package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String company;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Address location;
    private String type;
    private boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "job_creator_id", nullable = false)
    private User jobCreator;

    @OneToMany
    private List<User> applicants;
}
