package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.controllers;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.dto.PostDTO;
import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }


    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

    @PutMapping(path = "/{postId}")
    public PostDTO updatePostById(@PathVariable Long postId, @RequestBody PostDTO inputPost) {
        return postService.updatePostById(postId, inputPost);
    }
}
