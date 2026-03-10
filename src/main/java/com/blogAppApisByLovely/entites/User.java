package com.blogAppApisByLovely.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)

private int id;

@Column(name ="user_name",nullable = false,length=100)//if i want to change name in db table at column
private String name;


private String email;



private String password;



private String about;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> Posts = new ArrayList<>();
}
