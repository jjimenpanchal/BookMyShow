package com.example.bookmyshow.services;

import com.example.bookmyshow.models.Theatre;
import com.example.bookmyshow.repositories.TheatreRepository;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {
    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Theatre addTheatre(String name, String address) {
        Theatre theatre = new Theatre();
        theatre.setAddress(address);
        theatre.setName(name);

        return theatreRepository.save(theatre);
    }
}
