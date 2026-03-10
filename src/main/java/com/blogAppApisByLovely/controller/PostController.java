package com.blogAppApisByLovely.controller;
import com.blogAppApisByLovely.payloads.PostDto;
import com.blogAppApisByLovely.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/posts")
public class PostController {
  @Autowired
  private PostService postService;
  @PostMapping("/user/{userId}/category/{categoryId}")
  public ResponseEntity<PostDto> createPost(
          @RequestBody PostDto postDto,
          @PathVariable Integer userId,
          @PathVariable Integer categoryId){
    PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
  }
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
    List<PostDto> posts = this.postService.getPostsByUser(userId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
    List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }
}