package com.blogAppApisByLovely.repositories;

import com.blogAppApisByLovely.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

}
