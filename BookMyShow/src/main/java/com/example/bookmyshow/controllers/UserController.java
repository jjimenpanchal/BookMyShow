package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dto.CreateUserRequestDto;
import com.example.bookmyshow.dto.CreateUserResponseDto;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        User user = userService.createUser(requestDto.getName(), requestDto.getEmail());
        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setUser(user);
        return responseDto;
    }
}
