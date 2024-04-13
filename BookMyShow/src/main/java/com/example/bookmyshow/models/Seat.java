package com.example.bookmyshow.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{
    int row;
    int col;
    public Seat(SeatType seatType){
        this.seatType = seatType;
    }
    @Enumerated(EnumType.STRING)
    SeatType seatType;

    public Seat() {

    }
}
