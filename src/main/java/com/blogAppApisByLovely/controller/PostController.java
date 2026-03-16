package com.blogAppApisByLovely.controller;
import com.blogAppApisByLovely.config.AppConstants;
import com.blogAppApisByLovely.payloads.ApiResponse;
import com.blogAppApisByLovely.payloads.PostDto;
import com.blogAppApisByLovely.payloads.PostResponse;
import com.blogAppApisByLovely.services.FileService;
import com.blogAppApisByLovely.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  @Autowired
  private PostService postService;

  @Autowired
  private FileService fileService;
  @Value("${project.image}")
 private String path;


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
          @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
          @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
          @RequestParam(value ="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
          @RequestParam(value = "sortDirection",defaultValue =AppConstants.SORT_DIR,required = false) String sortDirection
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
    //search
  @GetMapping("/search/{Keywords}")
  public ResponseEntity<List<PostDto>> searchByTitle(
          @PathVariable("Keywords") String Keywords
  ){
    List<PostDto> result = this.postService.searchPosts((Keywords));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping("/image/upload/{postId}")
  public ResponseEntity<PostDto> uploadPostImage(
          @RequestParam("image")MultipartFile image,
          @PathVariable Integer postId
          ) throws IOException
  {
    PostDto postDto =  this.postService.getPostById(postId);

    String fileName = this.fileService.uploadImage(path,image);

postDto.setImageName((fileName));
PostDto updatePost = this.postService.updatePost(postDto,postId);
return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
  }

  //merge to serve files
    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    )throws IOException {
      InputStream resource = this.fileService.getResource(path,imageName);
      response.setContentType(MediaType.IMAGE_JPEG_VALUE);
      StreamUtils.copy(resource,response.getOutputStream());
    }



}