package com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.services;

import com.vatsaladhiya.vatsal.prodReadyFeatures.prodReadyFeatures.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
    PostDTO createNewPost(PostDTO inputPost);
    PostDTO getPostById(Long postId);
    PostDTO updatePostById(Long postId, PostDTO inputPost);
}
