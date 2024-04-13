package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dto.AddShowRequestDto;
import com.example.bookmyshow.dto.AddShowResponseDto;
import com.example.bookmyshow.exceptions.AuditoriumNotFound;
import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowController {
    private final ShowService showService;

    @Autowired
    ShowController(ShowService showService) {
        this.showService = showService;
    }

    public AddShowResponseDto addShow(AddShowRequestDto requestDto) throws AuditoriumNotFound {
        Show show = showService.addShow(requestDto.getAudiId(), requestDto.getStartTime(), requestDto.getEndTime(),
                requestDto.getLanguage());
        AddShowResponseDto responseDto = new AddShowResponseDto();
        responseDto.setShow(show);
        return responseDto;
    }
}
