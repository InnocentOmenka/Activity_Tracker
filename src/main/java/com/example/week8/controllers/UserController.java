package com.example.week8.controllers;

import com.example.week8.dto.UserDto;
import com.example.week8.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        userService.userSignUp(userDto);
        return new ResponseEntity<>("Registered successfully", HttpStatus.CREATED);
    }
//
//    @PostMapping("/login")
//    public ResponseEntity createUser(@RequestBody UserDto userDto) {
//        userService.userSignUp(userDto);
//        return new ResponseEntity("Welcome back", HttpStatus.FOUND);
//
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto usersDto){
        return new ResponseEntity<>(userService.login(usersDto), HttpStatus.ACCEPTED);
    }
}
