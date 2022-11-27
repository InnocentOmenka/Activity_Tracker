package com.example.week8.controllers;

import com.example.week8.dto.UserDto;
import com.example.week8.models.User;
import com.example.week8.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;


//    @PostMapping("/login")
//    public User userSignUp(UserDto userDto) {
//        return userService.userSignUp(userDto);
//    }
}
