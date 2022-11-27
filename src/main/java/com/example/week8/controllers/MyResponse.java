package com.example.week8.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MyResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;

}
