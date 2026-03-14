package com.blogAppApisByLovely.controller;
import com.blogAppApisByLovely.entites.Post;
import com.blogAppApisByLovely.payloads.ApiResponse;
import com.blogAppApisByLovely.payloads.PostDto;
import com.blogAppApisByLovely.payloads.PostResponse;
import com.blogAppApisByLovely.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class PostController {
  @Autowired
  private PostService postService;

  //create post
  @PostMapping("/user/{userId}/category/{categoryId}")
  public ResponseEntity<PostDto> createPost(
          @RequestBody PostDto postDto,
          @PathVariable Integer userId,
          @PathVariable Integer categoryId){
    PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
  }

  //get post by users
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
    List<PostDto> posts = this.postService.getPostsByUser(userId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  //get post by category
  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
    List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  //get all posts
  @GetMapping("/")
  public ResponseEntity<PostResponse> getAllPosts(
          @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
          @RequestParam(value ="sortBy",defaultValue = "postId",required = false) String sortBy,
          @RequestParam(value = "sortDirection",defaultValue ="ASC",required = false) String sortDirection
  ){
    // Call service which now returns PostResponse instead of List
    PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize,sortBy,sortDirection);
    // Return the full response object with metadata
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }

  //get post by id
  @GetMapping("/{postId}")
  public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
    PostDto postDto = this.postService.getPostById(postId);
    return new ResponseEntity<>(postDto,HttpStatus.OK);
  }
//delete post
  @DeleteMapping("/{postId}")
  public ApiResponse deletePostById(@PathVariable Integer postId) {
    this.postService.deletePost(postId);
    return new ApiResponse("Post is successfully deleted", true);
  }
    //update post
    @PutMapping("/{postId}")
            public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
      PostDto updatePost = this.postService.updatePost(postDto,postId);
      return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }



}