package com.example.bookmyshow.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddCityRequestDto {
    private String name;
    private Long pincode;
}
