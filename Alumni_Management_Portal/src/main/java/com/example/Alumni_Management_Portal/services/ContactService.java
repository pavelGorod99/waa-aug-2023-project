package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.ContactDto;
import com.example.Alumni_Management_Portal.entities.Contact;

import java.util.List;

public interface ContactService {
    List<ContactDto> getAll();
    ContactDto getById(int id);
    ContactDto getByUserId(int userId);
    ContactDto create(ContactDto contactDto);
    void update(ContactDto contactDto);
    void delete(int id);
}