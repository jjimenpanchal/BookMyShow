package com.example.bookmyshow.dto;

import com.example.bookmyshow.models.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class AddShowRequestDto {
    private Date startTime;
    private Date endTime;

    private Long audiId;
    private Language language;
}
