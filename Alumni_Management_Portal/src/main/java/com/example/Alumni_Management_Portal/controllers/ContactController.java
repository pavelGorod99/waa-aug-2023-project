package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.ContactDto;
import com.example.Alumni_Management_Portal.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<ContactDto> contacts = contactService.getAll();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ContactDto> getContactByUserId(@PathVariable int userId){
        ContactDto contact = contactService.getByUserId(userId);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<ContactDto> createContact(@RequestBody ContactDto contactDto) {
        ContactDto createdContact = contactService.create(contactDto);
        return ResponseEntity.ok(createdContact);
    }

    @PutMapping
    public ResponseEntity<Void> updateContact(@RequestBody ContactDto contactDto) {
        contactService.update(contactDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        contactService.delete(id);
        return ResponseEntity.ok().build();
    }
}