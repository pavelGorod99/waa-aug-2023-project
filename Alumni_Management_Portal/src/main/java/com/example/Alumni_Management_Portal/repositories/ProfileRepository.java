package com.example.Alumni_Management_Portal.repositories;


import com.example.Alumni_Management_Portal.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
