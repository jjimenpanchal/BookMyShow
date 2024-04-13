package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.AuditoriumNotFound;
import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.AuditoriumRepository;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private final ShowRepository showRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final ShowSeatRepository showSeatRepository;

    @Autowired
    ShowService(AuditoriumRepository auditoriumRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository) {
        this.auditoriumRepository = auditoriumRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }

    public Show addShow(Long audiId, Date startTime, Date endTime, Language language) throws AuditoriumNotFound{
        Show show = new Show();
        show.setLanguage(language);
        show.setStartTime(startTime);
        show.setEndTime(endTime);

        Optional<Auditorium> optionalAudi = auditoriumRepository.findById(audiId);
        if (optionalAudi.isEmpty()) {
           throw new AuditoriumNotFound("Did not find auditorium with id: " + audiId + " while adding show");
        }

        show.setAuditorium(optionalAudi.get());
        Show savedShow = showRepository.save(show);

        List<ShowSeat> showSeats = new ArrayList<>();
        for (Seat seat : optionalAudi.get().getSeats()) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShow(savedShow);
            showSeat.setStatus(ShowSeatStatus.AVAILABLE);
            showSeats.add(showSeat);
        }

        showSeatRepository.saveAll(showSeats);
        savedShow.setShowseats(showSeats);
        return showRepository.save(savedShow);
    }
}
