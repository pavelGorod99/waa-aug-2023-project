package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.ProfileDto;
import com.example.Alumni_Management_Portal.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        List<ProfileDto> profiles = profileService.getAll();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable int id) {
        ProfileDto profile = profileService.getById(id);
        return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        ProfileDto createdProfile = profileService.create(profileDto);
        return ResponseEntity.ok(createdProfile);
    }

    @PutMapping
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileDto profileDto) {
        profileService.update(profileDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int id) {
        profileService.delete(id);
        return ResponseEntity.ok().build();
    }
}
