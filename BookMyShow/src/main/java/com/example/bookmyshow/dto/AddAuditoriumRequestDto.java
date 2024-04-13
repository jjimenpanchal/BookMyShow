package com.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddAuditoriumRequestDto {
    String name;
    Long TheatreId;
}
