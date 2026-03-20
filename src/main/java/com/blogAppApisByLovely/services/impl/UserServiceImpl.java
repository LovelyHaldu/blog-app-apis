package com.blogAppApisByLovely.services.impl;

import com.blogAppApisByLovely.entites.User;
import com.blogAppApisByLovely.exceptions.ResourceNotFoundException;
import com.blogAppApisByLovely.payloads.UserDto;
import com.blogAppApisByLovely.repositories.UserRepo;
import com.blogAppApisByLovely.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();//READ IT ONLINE
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepo.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
       user.setAbout(userDto.getAbout());

       User updatedUser = this.userRepo.save(user);
       UserDto userDto1 = this.userToDto(updatedUser);
       return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users
                .stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
     User user = this.userRepo.findById(userId)
             .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
   this.userRepo.delete(user);
    }
//basic DTO implementation
    public User dtoToUser(UserDto userDto) {
        User user =this.modelMapper.map(userDto,User.class);

//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }
    private UserDto userToDto(User user) {
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}

