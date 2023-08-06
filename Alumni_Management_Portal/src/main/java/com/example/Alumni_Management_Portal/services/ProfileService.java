package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.ProfileDto;
import com.example.Alumni_Management_Portal.entities.Profile;

import java.util.List;

public interface ProfileService {
    List<ProfileDto> getAll();
    ProfileDto getById(int id);
    ProfileDto create(ProfileDto profileDto);
    void update(ProfileDto profileDto);
    void delete(int id);
}
