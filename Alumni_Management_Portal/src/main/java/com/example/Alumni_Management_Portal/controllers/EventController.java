package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.EventDto;
import com.example.Alumni_Management_Portal.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/events")
public class EventController {

    public EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public void create(@RequestBody EventDto eventDto) {
        eventService.create(eventDto);
    }

    @PutMapping(path = "/attend")
    public void attendTheEvent(@RequestParam int userId, @RequestParam int eventId) {
        eventService.attendTheEvent(userId, eventId);
    }

    @GetMapping
    public List<EventDto> getAll() {
        return eventService.getAll();
    }

    @GetMapping(path = "/{id}")
    public EventDto getById(@PathVariable int id) {
        return eventService.getById(id);
    }
}
