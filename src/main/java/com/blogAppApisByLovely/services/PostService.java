package com.blogAppApisByLovely.services;
import com.blogAppApisByLovely.payloads.PostDto;
import com.blogAppApisByLovely.payloads.PostResponse;

import java.util.List;
public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);
}