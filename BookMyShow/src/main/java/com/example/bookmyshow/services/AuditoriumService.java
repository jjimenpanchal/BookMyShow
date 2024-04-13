package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.AuditoriumNotFound;
import com.example.bookmyshow.exceptions.TheatreNotFoundException;
import com.example.bookmyshow.models.Auditorium;
import com.example.bookmyshow.models.Seat;
import com.example.bookmyshow.models.SeatType;
import com.example.bookmyshow.models.Theatre;
import com.example.bookmyshow.repositories.AuditoriumRepository;
import com.example.bookmyshow.repositories.SeatRepository;
import com.example.bookmyshow.repositories.TheatreRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuditoriumService {
    AuditoriumRepository AuditoriumRepository;
    TheatreRepository TheatreRepository;
    SeatRepository seatRepository;
    AuditoriumService(AuditoriumRepository AuditoriumRepository, TheatreRepository TheatreRepository,
                      SeatRepository seatRepository) {
        this.AuditoriumRepository = AuditoriumRepository;
        this.TheatreRepository = TheatreRepository;
        this.seatRepository = seatRepository;
    }

    public Auditorium addAuditorium(String name, Long theatreId) throws TheatreNotFoundException {
        Optional<Theatre> theatreOptional = TheatreRepository.findById(theatreId);
        if(theatreOptional.isEmpty()) {
            throw new TheatreNotFoundException("Theatre not found");
        }
        Auditorium auditorium = new Auditorium();
        auditorium.setName(name);
        auditorium.setTheatre(theatreOptional.get());
        Auditorium savedAudi = AuditoriumRepository.save(auditorium);
        theatreOptional.get().getAuditoriums().add(savedAudi);
        TheatreRepository.save(theatreOptional.get());
        return savedAudi;
    }

    public Auditorium addSeats(Long audiId, Map<SeatType, Integer>seats) throws AuditoriumNotFound {
        Optional<Auditorium> audiOptional = AuditoriumRepository.findById(audiId);
        if (audiOptional.isEmpty()) {
            throw new AuditoriumNotFound("Auditorium not found");
        }
        Auditorium audi = audiOptional.get();

        List<Seat> newSeats = new ArrayList<>();
        for (Map.Entry<SeatType, Integer> entry : seats.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                newSeats.add(new Seat(entry.getKey()));
            }
        }

        seatRepository.saveAll(newSeats);
        audi.setSeats(newSeats);
        return AuditoriumRepository.save(audi);
    }
}
