package com.blogAppApisByLovely.services;


import com.blogAppApisByLovely.payloads.CommentDto;

public interface CommentService {
//interface mai bydefault public hota h

 CommentDto createComment(CommentDto commentDto ,Integer postId);

 void deleteComment(Integer commentId);

}
