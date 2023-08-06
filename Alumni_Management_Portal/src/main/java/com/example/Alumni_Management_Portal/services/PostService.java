package com.example.Alumni_Management_Portal.services;

import com.example.Alumni_Management_Portal.dto.PostDto;

import java.util.List;

public interface PostService {

    void create(PostDto postDto);
    void update(PostDto postDto);
    List<PostDto> getAll();
    PostDto getById(int id);
}
