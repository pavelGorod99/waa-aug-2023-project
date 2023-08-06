package com.example.Alumni_Management_Portal.controllers;

import com.example.Alumni_Management_Portal.dto.PostDto;
import com.example.Alumni_Management_Portal.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public void create(@RequestBody PostDto postDto) {
        postService.create(postDto);
    }

    @PutMapping
    public void update(@RequestBody PostDto postDto) {
        postService.update(postDto);
    }

    @GetMapping(path = "/{id}")
    public PostDto getById(@PathVariable int id) {
        return postService.getById(id);
    }

    @GetMapping
    public List<PostDto> getAll() {
        return postService.getAll();
    }
}
