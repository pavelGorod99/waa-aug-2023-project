package com.example.Alumni_Management_Portal.repositories;

import com.example.Alumni_Management_Portal.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findAllByLocation_City(String city);
    List<Job> findAllByLocation_State(String state);
    List<Job> findAllByCompany(String company);
}
