package com.blogAppApisByLovely.controller;

import com.blogAppApisByLovely.payloads.ApiResponse;
import com.blogAppApisByLovely.payloads.UserDto;
import com.blogAppApisByLovely.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    //POST
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
       UserDto createUserDto =  this.userService.createUser(userDto);
       return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //PUT--update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer Uid){
       UserDto updatedUser = this.userService.updateUser(userDto,Uid);
       return ResponseEntity.ok(updatedUser);
    }
    //DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("userId") Integer Uid){
         this.userService.deleteUser(Uid);
//         return new ResponseEntity(Map.of("message","User Deleted Successfull"),HttpStatus.OK);
        return new ResponseEntity(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
    }
    //GET
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
  return ResponseEntity.ok(this.userService.getAllUsers());
     }
     @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer Uid) {
        return ResponseEntity.ok(this.userService.getUserById(Uid));
     }
}
