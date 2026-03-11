package com.blogAppApisByLovely.services.impl;
import com.blogAppApisByLovely.entites.Category;
import com.blogAppApisByLovely.entites.Post;
import com.blogAppApisByLovely.entites.User;
import com.blogAppApisByLovely.exceptions.ResourceNotFoundException;
import com.blogAppApisByLovely.payloads.PostDto;
import com.blogAppApisByLovely.repositories.CategoryRepo;
import com.blogAppApisByLovely.repositories.PostRepo;
import com.blogAppApisByLovely.repositories.UserRepo;
import com.blogAppApisByLovely.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","User id",userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category id",categoryId));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        return posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
         Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
         Post updatePost = this.postRepo.save(post);

         return this.modelMapper.map(updatePost,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId){
       Post post =  this.postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> allPosts = this.postRepo.findAll();
         return allPosts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post =  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post Id",postId));
       return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> searchPosts(String keyword) { return List.of(); }
}