package com.example.Alumni_Management_Portal.dto;

import com.example.Alumni_Management_Portal.entities.PostType;
import com.example.Alumni_Management_Portal.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PostDto {

    private int id;
    private String title;
    private Date createdDate;
    private String description;
    private String imagePath;
    private PostType postType;
    private User creator;
}
