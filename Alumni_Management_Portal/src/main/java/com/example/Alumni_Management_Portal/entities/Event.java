package com.example.Alumni_Management_Portal.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    private String type;

    @ManyToOne
    @JoinColumn(name = "event_creator_id")
    private User eventCreator;

    @OneToMany
    private List<User> attendance;
}
