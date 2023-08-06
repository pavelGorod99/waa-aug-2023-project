package com.example.Alumni_Management_Portal.repositories;

import com.example.Alumni_Management_Portal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
