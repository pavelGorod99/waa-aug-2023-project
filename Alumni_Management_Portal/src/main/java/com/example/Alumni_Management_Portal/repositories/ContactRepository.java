package com.example.Alumni_Management_Portal.repositories;


import com.example.Alumni_Management_Portal.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact findByUserId(int userId);
}