package com.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserRequestDto {
    private String email;
    private String name;
}
