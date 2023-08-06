package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.EventDto;
import com.example.Alumni_Management_Portal.entities.Event;
import com.example.Alumni_Management_Portal.entities.User;
import com.example.Alumni_Management_Portal.repositories.EventRepository;
import com.example.Alumni_Management_Portal.services.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private UserServiceImpl userService;
    private ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, UserServiceImpl userService) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void create(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        eventRepository.save(event);
    }

    @Override
    public void attendTheEvent(int userId, int eventId) {
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            User user = userService.getEntityById(userId);
            if (user != null) {
                Event event = eventOptional.get();
                List<User> userList = event.getAttendance();
                userList.add(user);
                eventRepository.save(event);
            }
        }
    }

    @Override
    public List<EventDto> getAll() {
        List<Event> eventList = eventRepository.findAll();
        List<EventDto> eventDtoList = new ArrayList<>();
        eventList.forEach(event -> {
            EventDto eventDto = modelMapper.map(event, EventDto.class);
            eventDtoList.add(eventDto);
        });
        return eventDtoList;
    }

    @Override
    public EventDto getById(int id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()) {
            EventDto eventDto = modelMapper.map(event.get(), EventDto.class);
            return eventDto;
        } else return null;
    }
}
