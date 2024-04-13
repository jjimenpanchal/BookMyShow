package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Ticket extends BaseModel{
    double totalAmount;
    private Date timeOfBooking;

    @ManyToOne
    User user;

    @ManyToOne
    Show show;

    @ManyToMany // if no cancellation than @OneToMany
    List<ShowSeat> showSeats;

    @Enumerated(EnumType.STRING)
    TicketStatus ticketStatus;
}
