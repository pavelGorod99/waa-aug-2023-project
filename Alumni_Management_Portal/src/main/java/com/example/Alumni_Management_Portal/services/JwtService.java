package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {

    String extractUserName(String token);

    String generateToken(User baseUser);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);
}
