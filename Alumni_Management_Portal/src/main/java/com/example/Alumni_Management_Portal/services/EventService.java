package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.EventDto;

import java.util.List;

public interface EventService {
    void create(EventDto eventDto);
    void attendTheEvent(int userId, int eventId);
    List<EventDto> getAll();
    EventDto getById(int id);
}
