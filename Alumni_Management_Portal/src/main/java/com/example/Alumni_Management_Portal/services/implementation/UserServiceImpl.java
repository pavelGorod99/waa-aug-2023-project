package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.dto.LoginRequestDto;
import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.EmailAlreadyExistsException;
import com.example.Alumni_Management_Portal.entities.Job;
import com.example.Alumni_Management_Portal.entities.ResourceNotFoundException;
import com.example.Alumni_Management_Portal.entities.User;
import com.example.Alumni_Management_Portal.repositories.UserRepository;
import com.example.Alumni_Management_Portal.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CrossOrigin
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public User getEntityById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else return null;
    }

    @Override
    public UserDto getById(int id) {
        User user = getEntityById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public String authenticateUser(LoginRequestDto loginRequestDto) throws ResourceNotFoundException{
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        Optional<User> opUser= userRepository.findByEmail(loginRequestDto.getEmail());
        if(opUser.isPresent())
        {
            User dbUser= opUser.get();
            if(bcrypt.matches(loginRequestDto.getPassword(), dbUser.getPassword())){
                return "Authenticated User";
            } else{
                return "Incorrect Password";
            }
        }
        throw new ResourceNotFoundException("User is not found");
    }

    public void logOut(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setIsActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public String create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("A user is already registered with this email address: " + user.getEmail());
        }
        BCryptPasswordEncoder bcrypt= new BCryptPasswordEncoder();
        String encryptedPwd= bcrypt.encode(user.getPassword());
        user.setPassword(encryptedPwd);
        System.out.println("User email: " + user.getEmail());
        System.out.println("User id: " + user.getId());
        User savedUser = userRepository.save(user);
        return savedUser.getFirstName() + " added to database successfully";
    }

    @Override
    public void update(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userDto.getId()));
        modelMapper.map(userDto, existingUser);
        userRepository.save(existingUser);
    }

    @Override
    public UserDto addJobExperience(int userId, JobDto jobDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Job job = new Job();
        job.setCompany(jobDto.getCompany());
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setLocation(jobDto.getLocation());
        job.setType(jobDto.getType());

        user.getJobExperiences().add(job);
        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }


    @Override
    public void delete(int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No user found with id " + id));

        user.setIsDeleted(true);
        userRepository.save(user);
    }

}
