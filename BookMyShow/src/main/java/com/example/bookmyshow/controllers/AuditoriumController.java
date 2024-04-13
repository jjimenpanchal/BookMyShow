package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dto.AddAuditoriumRequestDto;
import com.example.bookmyshow.dto.AddAuditoriumResponseDto;
import com.example.bookmyshow.exceptions.AuditoriumNotFound;
import com.example.bookmyshow.exceptions.TheatreNotFoundException;
import com.example.bookmyshow.models.Auditorium;
import com.example.bookmyshow.models.SeatType;
import com.example.bookmyshow.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class AuditoriumController {
    private AuditoriumService auditoriumService;

    @Autowired
    AuditoriumController(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    public AddAuditoriumResponseDto addAuditorium(AddAuditoriumRequestDto requestDto) throws TheatreNotFoundException {
        Auditorium audi = auditoriumService.addAuditorium(requestDto.getName(), requestDto.getTheatreId());
        AddAuditoriumResponseDto responseDto = new AddAuditoriumResponseDto();
        responseDto.setAuditorium(audi);
        return responseDto;
    }

    // we could have used AddAuditoriumResponseDto instead of Auditorium but as of now we are using Auditorium
    public Auditorium addSeats(Long audiId, Map<SeatType, Integer> seats) throws AuditoriumNotFound {
        Auditorium audi = auditoriumService.addSeats(audiId, seats);

        return audi;
    }
}
