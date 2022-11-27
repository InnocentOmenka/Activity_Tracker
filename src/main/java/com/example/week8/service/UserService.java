package com.example.week8.service;

import com.example.week8.dto.UserDto;
import com.example.week8.models.User;
import com.example.week8.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    User userSignUp(UserDto userDto);
    String login(UserDto userDto);
}
