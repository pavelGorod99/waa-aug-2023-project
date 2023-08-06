package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.dto.UserDto;
import com.example.Alumni_Management_Portal.entities.Job;
import com.example.Alumni_Management_Portal.entities.User;
import com.example.Alumni_Management_Portal.repositories.JobRepository;
import com.example.Alumni_Management_Portal.services.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private ModelMapper modelMapper;
    private UserServiceImpl userService;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, UserServiceImpl userService) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void create(JobDto jobDto) {
        Job job = modelMapper.map(jobDto, Job.class);
        jobRepository.save(job);
    }

    @Override
    public void update(JobDto jobDto) {
        Optional<Job> jobOptional = jobRepository.findById(jobDto.getId());
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setCompany(jobDto.getCompany());
            job.setTitle(jobDto.getTitle());
            job.setDescription(jobDto.getDescription());
            job.setLocation(jobDto.getLocation());
            job.setType(jobDto.getType());
            jobRepository.save(job);
        }
    }

    @Override
    public List<JobDto> getAll() {
        List<Job> jobList = jobRepository.findAll();
        List<JobDto> jobDtoList = convertJobListToJobDtoList(jobList);
        return jobDtoList;
    }

    @Override
    public void applyForJob(int userId, int jobId) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            UserDto userDto = userService.getById(userId);
            User user = modelMapper.map(userDto, User.class);
            List<User> userList = job.getApplicants();
            userList.add(user);
            jobRepository.save(job);
        } else throw new RuntimeException("Job with id: " + jobId + " not found");
    }

    @Override
    public void closeTheJob(int id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setAvailable(false);
            jobRepository.save(job);
        }
    }

    @Override
    public List<JobDto> findAllByCity(String city) {
        List<Job> jobList = jobRepository.findAllByLocation_City(city);
        List<JobDto> jobDtoList = convertJobListToJobDtoList(jobList);
        return jobDtoList;
    }

    @Override
    public List<JobDto> findAllByState(String state) {
        List<Job> jobList = jobRepository.findAllByLocation_State(state);
        List<JobDto> jobDtoList = convertJobListToJobDtoList(jobList);
        return jobDtoList;
    }

    @Override
    public List<JobDto> findAllByCompany(String company) {
        List<Job> jobList = jobRepository.findAllByCompany(company);
        List<JobDto> jobDtoList = convertJobListToJobDtoList(jobList);
        return jobDtoList;
    }

    private List<JobDto> convertJobListToJobDtoList(List<Job> jobList) {
        List<JobDto> jobDtoList = new ArrayList<>();
        jobList.forEach(job -> {
            JobDto jobDto = modelMapper.map(job, JobDto.class);
            jobDtoList.add(jobDto);
        });
        return jobDtoList;
    }
}
