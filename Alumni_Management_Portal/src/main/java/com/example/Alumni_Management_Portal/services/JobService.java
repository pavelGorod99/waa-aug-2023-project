package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.JobDto;

import java.util.List;

public interface JobService {
    void create(JobDto jobDto);
    void update(JobDto jobDto);
    List<JobDto> getAll();
    void applyForJob(int userId, int jobId);
    void closeTheJob(int jobId);
    List<JobDto> findAllByCity(String city);
    List<JobDto> findAllByState(String state);
    List<JobDto> findAllByCompany(String company);
}
