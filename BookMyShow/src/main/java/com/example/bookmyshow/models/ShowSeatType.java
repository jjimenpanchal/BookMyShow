package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "show_seat_type_mapping")
public class ShowSeatType extends BaseModel{

//    SST : Show
//     1 : 1
//     M : 1
    @ManyToOne
    Show show;

    @Enumerated(EnumType.STRING)
    SeatType seatType;
    double price;
}
