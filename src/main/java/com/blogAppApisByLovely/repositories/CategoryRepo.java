package com.blogAppApisByLovely.repositories;

import com.blogAppApisByLovely.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
