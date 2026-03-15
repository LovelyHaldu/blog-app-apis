package com.blogAppApisByLovely.repositories;

import com.blogAppApisByLovely.entites.Category;
import com.blogAppApisByLovely.entites.Post;
import com.blogAppApisByLovely.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    //findBy methods in jpa

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}
