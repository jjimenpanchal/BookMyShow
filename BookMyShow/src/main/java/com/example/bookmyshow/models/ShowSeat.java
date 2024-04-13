package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ShowSeat extends BaseModel{
    @ManyToOne
    Seat seat;
    @ManyToOne
    Show show;
    @Enumerated(EnumType.STRING)
    ShowSeatStatus status;
}
