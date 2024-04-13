package com.example.bookmyshow.dto;

import com.example.bookmyshow.models.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserResponseDto {
    private User user;
}
