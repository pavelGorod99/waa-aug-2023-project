package com.example.Alumni_Management_Portal.services.implementation;

import com.example.Alumni_Management_Portal.dto.PostDto;
import com.example.Alumni_Management_Portal.entities.Post;
import com.example.Alumni_Management_Portal.repositories.PostRepository;
import com.example.Alumni_Management_Portal.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(PostDto postDto) {
        Optional<Post> optionalPost = postRepository.findById(postDto.getId());
        if (optionalPost.isEmpty()) {
            Post post = modelMapper.map(postDto, Post.class);
            postRepository.save(post);
        }
    }

    @Override
    public void update(PostDto postDto) {
        Optional<Post> optionalPost = postRepository.findById(postDto.getId());
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(postDto.getTitle());
            post.setPostType(postDto.getPostType());
            post.setCreatedDate(postDto.getCreatedDate());
            post.setDescription(postDto.getDescription());
            post.setImagePath(postDto.getImagePath());
            post.setCreator(postDto.getCreator());
            postRepository.save(post);
        }
    }

    @Override
    public List<PostDto> getAll() {
        List<Post> postList = postRepository.findAll();
        List<PostDto> postDtoList = new ArrayList<>();
        postList.forEach(post -> {
            PostDto postDto = modelMapper.map(post, PostDto.class);
            postDtoList.add(postDto);
        });
        return postDtoList;
    }

    @Override
    public PostDto getById(int id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            PostDto postDto = modelMapper.map(optionalPost.get(), PostDto.class);
            return postDto;
        } else return null;
    }
}
