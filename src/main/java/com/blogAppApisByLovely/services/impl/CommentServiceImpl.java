package com.blogAppApisByLovely.services.impl;

import com.blogAppApisByLovely.entites.Comment;
import com.blogAppApisByLovely.entites.Post;
import com.blogAppApisByLovely.exceptions.ResourceNotFoundException;
import com.blogAppApisByLovely.payloads.CommentDto;
import com.blogAppApisByLovely.repositories.CommentRepo;
import com.blogAppApisByLovely.repositories.PostRepo;
import com.blogAppApisByLovely.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post",postId));
         Comment comment = this.modelMapper.map(commentDto, Comment.class);
         comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
   Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Comment",commentId));
   this.commentRepo.delete(com);

    }
}
