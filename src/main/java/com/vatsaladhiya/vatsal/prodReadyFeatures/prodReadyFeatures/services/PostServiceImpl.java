package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.services;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.dto.PostDTO;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.entities.PostEntity;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.exceptions.ResourceNotFoundException;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public PostDTO updatePostById(Long postId, PostDTO inputPost) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        inputPost.setId(postId);
        modelMapper.map(inputPost, postEntity);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }
}
