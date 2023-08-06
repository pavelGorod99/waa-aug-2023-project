package com.example.Alumni_Management_Portal;

import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.Role;
import com.example.Alumni_Management_Portal.entities.User;
import com.example.Alumni_Management_Portal.repositories.RoleRepository;
import com.example.Alumni_Management_Portal.services.implementation.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleCommandLineRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleCommandLineRunner(RoleRepository roleRepository, UserServiceImpl userService, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        Role role1 = new Role();
//        role1.setId(1);
        role1.setRole("student");

        Role role2 = new Role();
//        role2.setId(2);
        role2.setRole("faculty");

        Role role3 = new Role();
//        role3.setId(3);
        role3.setRole("admin");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);

        User user = new User();

        user.setFirstName("Berhane");
        user.setLastName("Smith");
        user.setPassword("blabla");
        user.setEmail("some@gmail.com");

        user.setRole(role1);

        user.setIsActive(true);
        user.setIsDeleted(false);

        UserDto userDto = modelMapper.map(user, UserDto.class);
        String newUser = userService.create(userDto);

        System.out.println(newUser);

        List<UserDto> userDtoList = userService.getAll();
        userDtoList.forEach(userDto1 -> {
            System.out.println(userDto1.getFirstName() + " " + userDto1.getLastName() + " " + userDto1.getPassword());

        });
    }
}
