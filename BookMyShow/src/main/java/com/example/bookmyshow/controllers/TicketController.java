package com.example.bookmyshow.controllers;

import com.example.bookmyshow.dto.BookTicketRequestDto;
import com.example.bookmyshow.dto.BookTicketResponseDto;
import com.example.bookmyshow.exceptions.ShowNotFoundException;
import com.example.bookmyshow.exceptions.ShowSeatNotAvailable;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Ticket;
import com.example.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    TicketService ticketService;

    @Autowired
    TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public BookTicketResponseDto bookTicket(BookTicketRequestDto requestDto) throws UserNotFoundException,
            ShowNotFoundException, ShowSeatNotAvailable {
        Ticket ticket = ticketService.bookTicket(requestDto.getUserId(), requestDto.getShowId(),
                requestDto.getShowSeatIds());
        BookTicketResponseDto responseDto = new BookTicketResponseDto();
        responseDto.setTicket(ticket);
        return responseDto;
    }
}
