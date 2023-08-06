package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.ContactDto;
import com.example.Alumni_Management_Portal.entities.Contact;
import com.example.Alumni_Management_Portal.entities.ResourceNotFoundException;
import com.example.Alumni_Management_Portal.repositories.ContactRepository;
import com.example.Alumni_Management_Portal.services.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    private final ModelMapper modelMapper;


    public ContactServiceImpl(ContactRepository contactRepository, ModelMapper modelMapper) {

        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ContactDto> getAll() {
        List<Contact> contactList= contactRepository.findAll();
        List<ContactDto> contactDtoList= new ArrayList<>();
        contactList.forEach(contact->{
            ContactDto contactDto= modelMapper.map(contact,ContactDto.class);
            contactDtoList.add(contactDto);
        });
        return contactDtoList;
    }

    @Override
    public ContactDto getById(int id) {
        Optional<Contact> contact= contactRepository.findById(id);
        if(contact.isPresent()){
            ContactDto contactDto=modelMapper.map(contact,ContactDto.class);
            return contactDto;
        } else throw new ResourceNotFoundException("Contact not found with id " + id);
    }

    @Override
    public ContactDto getByUserId(int userId) {
        Contact contact = contactRepository.findByUserId(userId);
        if (contact != null) {
            return modelMapper.map(contact, ContactDto.class);
        } else {
            throw new ResourceNotFoundException("Contact not found for user id " + userId);
        }
    }

    @Override
    public ContactDto create(ContactDto contactDto) {
        Contact contact = modelMapper.map(contactDto, Contact.class);
        Contact savedContact = contactRepository.save(contact);
        return modelMapper.map(savedContact, ContactDto.class);
    }

    @Override
    public void update(ContactDto contactDto) {
        Optional<Contact> optionalExistingContact = contactRepository.findById(contactDto.getId());
        if (optionalExistingContact.isPresent()) {
            Contact existingContact = optionalExistingContact.get();
            modelMapper.map(contactDto, existingContact);
            contactRepository.save(existingContact);
        } else {
            throw new ResourceNotFoundException("Contact not found with id " + contactDto.getId());
        }
    }

    @Override
    public void delete(int id) {
        contactRepository.deleteById(id);
    }
}
