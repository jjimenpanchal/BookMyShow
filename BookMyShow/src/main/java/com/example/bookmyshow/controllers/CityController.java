package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dto.AddCityRequestDto;
import com.example.bookmyshow.dto.AddCityResponseDto;
import com.example.bookmyshow.models.City;
import com.example.bookmyshow.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CityController {
    CityService cityService;

    @Autowired
    CityController(CityService cityService) {
        this.cityService = cityService;
    }

    public AddCityResponseDto addCity(AddCityRequestDto addCityRequestDto) {

        City city = cityService.addCity(addCityRequestDto.getName(), addCityRequestDto.getPincode());
        AddCityResponseDto addCityResponseDto = new AddCityResponseDto();
        addCityResponseDto.setCity(city);

        return addCityResponseDto;
    }
}
