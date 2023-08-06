package com.example.Alumni_Management_Portal.dto;

import com.example.Alumni_Management_Portal.entities.Event;
import com.example.Alumni_Management_Portal.entities.User;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EventAttendanceDto {
    private Event event;
    private List<User> attendance;
}
