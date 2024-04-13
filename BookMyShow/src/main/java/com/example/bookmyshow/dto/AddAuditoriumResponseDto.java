package com.example.bookmyshow.dto;

import com.example.bookmyshow.models.Auditorium;
import com.example.bookmyshow.models.Theatre;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddAuditoriumResponseDto {
    Auditorium auditorium;
}
