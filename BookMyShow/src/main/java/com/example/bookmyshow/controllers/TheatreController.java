package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dto.AddTheatreRequestDto;
import com.example.bookmyshow.dto.AddTheatreResponseDto;
import com.example.bookmyshow.models.Theatre;
import com.example.bookmyshow.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TheatreController {
    private final TheatreService theatreService;

    @Autowired
    TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public AddTheatreResponseDto addTheatre(AddTheatreRequestDto requestDto) {
        Theatre theatre = theatreService.addTheatre(requestDto.getName(), requestDto.getAddress());
        AddTheatreResponseDto responseDto = new AddTheatreResponseDto();
        responseDto.setTheatre(theatre);
        return responseDto;
    }
}
