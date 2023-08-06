package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.dto.LoginRequestDto;
import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.EmailAlreadyExistsException;
import com.example.Alumni_Management_Portal.entities.ResourceNotFoundException;
import com.example.Alumni_Management_Portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {

        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {
        try {
            String newUserDto = userService.create(userDto);
            return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
        } catch (EmailAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authenticateUsers")
    public String authenticateUser(@RequestBody LoginRequestDto loginRequestDto){
        return userService.authenticateUser(loginRequestDto);
    }


    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserDto userDto) {
        System.out.println("I am here");
        try {
            userService.update(userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/job-experiences")
    public ResponseEntity<UserDto> addJobExperience(@PathVariable int id, @RequestBody JobDto jobDto) {
        UserDto userDto = userService.addJobExperience(id, jobDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}