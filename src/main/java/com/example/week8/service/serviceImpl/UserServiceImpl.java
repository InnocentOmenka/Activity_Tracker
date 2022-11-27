package com.example.week8.service.serviceImpl;

import com.example.week8.dto.UserDto;
import com.example.week8.models.User;
import com.example.week8.repositories.UserRepository;
import com.example.week8.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User userSignUp(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public String login(UserDto userDto) {
        User user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        if(!Objects.equals(user.getPassword(), userDto.getPassword()) || !Objects.equals(user.getEmail(), userDto.getEmail())){
            return "Invalid Email or Password";
        }
        return "Login Successfully";
    }
}
