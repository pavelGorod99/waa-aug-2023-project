package com.example.Alumni_Management_Portal.dto;

import com.example.Alumni_Management_Portal.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class EventDto {
    private int id;

    private String title;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    private String type;

    private User eventCreator;
}
