package com.blogAppApisByLovely.repositories;

import com.blogAppApisByLovely.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
