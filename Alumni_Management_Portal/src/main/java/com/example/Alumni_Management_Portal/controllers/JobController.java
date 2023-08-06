package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.JobDto;
import com.example.Alumni_Management_Portal.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/jobs")
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public void create(@RequestBody JobDto jobDto) {
        jobService.create(jobDto);
    }

    @PutMapping
    public void update(@RequestBody JobDto jobDto) {
        jobService.update(jobDto);
    }

    @PutMapping(path = "/apply")
    public void applyForJob(@RequestParam int userId, @RequestParam int jobId) {
        jobService.applyForJob(userId, jobId);
    }

    @PutMapping(path = "/close-it/{id}")
    public void closeTheJob(@PathVariable int id) {
        jobService.closeTheJob(id);
    }

    @GetMapping
    public List<JobDto> getAll() {
        return jobService.getAll();
    }

    @GetMapping(path = "/by-city")
    public List<JobDto> getAllByCity(@RequestParam String city) {
        return jobService.findAllByCity(city);
    }

    @GetMapping(path = "/by-state")
    public List<JobDto> getAllByState(@RequestParam String state) {
        return jobService.findAllByState(state);
    }

    @GetMapping(path = "/by-company")
    public List<JobDto> getAllByCompany(@RequestParam String company) {
        return jobService.findAllByCompany(company);
    }
}
