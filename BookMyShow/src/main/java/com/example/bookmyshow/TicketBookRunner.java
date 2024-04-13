package com.example.bookmyshow;

import com.example.bookmyshow.controllers.TicketController;
import com.example.bookmyshow.dto.BookTicketRequestDto;
import com.example.bookmyshow.exceptions.ShowNotFoundException;
import com.example.bookmyshow.exceptions.ShowSeatNotAvailable;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.ShowSeat;

import java.util.List;

public class TicketBookRunner implements Runnable{
    private TicketController ticketController;
    private Long userId;
    private Long showId;
    private List<Long> ShowSeatIds;

    public TicketBookRunner(TicketController ticketController, Long userId, Long showId,
                            List<Long> ShowSeatIds) {
        this.ticketController = ticketController;
        this.userId = userId;
        this.showId = showId;
        this.ShowSeatIds = ShowSeatIds;
    }

    @Override
    public void run(){
        BookTicketRequestDto bookTicketRequestDto = new BookTicketRequestDto();
        bookTicketRequestDto.setUserId(userId);
        bookTicketRequestDto.setShowId(showId);
        bookTicketRequestDto.setShowSeatIds(ShowSeatIds);
        try {
            ticketController.bookTicket(bookTicketRequestDto);
        } catch (Exception e) {
            System.out.println("Exception occurred while booking ticket " + e.getMessage());
            e.printStackTrace();
        }
    }

}
