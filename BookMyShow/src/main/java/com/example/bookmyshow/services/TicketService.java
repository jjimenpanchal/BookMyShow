package com.example.bookmyshow.services;

import com.example.bookmyshow.exceptions.ShowNotFoundException;
import com.example.bookmyshow.exceptions.ShowSeatNotAvailable;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositories.ShowRepository;
import com.example.bookmyshow.repositories.ShowSeatRepository;
import com.example.bookmyshow.repositories.TicketRepository;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    UserRepository userRepository;
    ShowRepository showRepository;
    ShowSeatRepository showSeatRepository;
    TicketRepository ticketRepository;

    @Autowired
    TicketService(UserRepository userRepository, ShowRepository showRepository,
        ShowSeatRepository showSeatRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookTicket(Long userId, Long showId, List<Long> showSeatIds) throws UserNotFoundException,
            ShowNotFoundException, ShowSeatNotAvailable {

        //Fetch Show seats
        List<ShowSeat> showSeats = showSeatRepository.findByIdIn(showSeatIds);

        //check if all show seats are available
        for (ShowSeat showSeat : showSeats) {
            if (showSeat.getStatus() != ShowSeatStatus.AVAILABLE) {
                throw new ShowSeatNotAvailable("Show seat not available while booking ticket");
            }
        }

        //update status to locked
        for (ShowSeat showSeat : showSeats) {
            showSeat.setStatus(ShowSeatStatus.LOCKED);
            showSeatRepository.save(showSeat);
            System.out.println("Show seat locked" + showSeat.getId() + " " );
        }


//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException("User not found while booking ticket");
//        }else {
//            System.out.println("User found" + userOptional.get().getName() +
//                    " " + userOptional.get().getEmail());
//        }
//        User user = userOptional.get();
//
//        Optional<Show> showOptional = showRepository.findById(showId);
//        if (showOptional.isEmpty()) {
//            throw new ShowNotFoundException("Show not found while booking ticket");
//        }
//        Show show = showOptional.get();



        // return the ticket object
        Ticket ticket = new Ticket();
        ticket.setUser(userRepository.findById(userId).get());
        ticket.setShow(showRepository.findById(showId).get());
        ticket.setTimeOfBooking(new Date());
        ticket.setTotalAmount(100L); // assuming total amount is 100
        ticket.setTicketStatus(TicketStatus.PENDING);
        ticket.setShowSeats(showSeats);

        // assuming no cancellation is allowed
        // once the payment is done then only we can change the status to BOOKED
        return ticketRepository.save(ticket);
//        return ticket;
    }
}
