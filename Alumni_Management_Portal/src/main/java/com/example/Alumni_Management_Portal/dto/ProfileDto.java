package com.example.Alumni_Management_Portal.dto;

import com.example.Alumni_Management_Portal.entities.Course;
import com.example.Alumni_Management_Portal.entities.Industry;
import com.example.Alumni_Management_Portal.entities.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class ProfileDto {
    private int id;
    private User user;

    private String imagePath;
    private int graduationYear;

    private Industry industry;

    private String professionalAchievement;
    private String educationDetails;

    private Course course;
}