package com.example.Alumni_Management_Portal;

import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.Almuni;
import com.example.Alumni_Management_Portal.entities.User;
import com.example.Alumni_Management_Portal.repositories.RoleRepository;
import com.example.Alumni_Management_Portal.services.implementation.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleCommandLineRunnerWithEnum implements CommandLineRunner {

    private RoleRepository roleRepository;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @Autowired

    public RoleCommandLineRunnerWithEnum(UserServiceImpl userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//
//        user.setFirstName("Berhane");
//        user.setLastName("Smith");
//        user.setPassword("blabla");
//        user.setEmail("some@gmail.com");
//
//        user.setAlmuni(Almuni.STUDENT);
//
//        user.setIsActive(true);
//        user.setIsDeleted(false);
//
//        UserDto userDto = modelMapper.map(user, UserDto.class);
//        String newUser = userService.create(userDto);
//
//        System.out.println(newUser);
//
//        List<UserDto> userDtoList = userService.getAll();
//        userDtoList.forEach(userDto1 -> {
//            System.out.println(userDto1.getFirstName() + " " + userDto1.getLastName() + " " + userDto1.getPassword());
//        });

    }
}
