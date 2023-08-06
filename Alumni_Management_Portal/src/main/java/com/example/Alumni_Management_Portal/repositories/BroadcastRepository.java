package com.example.Alumni_Management_Portal.repositories;

import com.example.Alumni_Management_Portal.entities.Broadcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BroadcastRepository extends JpaRepository<Broadcast, Integer> {
}
