package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Auditorium extends BaseModel{
    String name;
    @ManyToOne
    Theatre theatre;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    List<AuditoriumFeatures> features;

    @OneToMany(fetch = FetchType.EAGER)
    List<Seat> seats;
}

//Auditorium : Seat => 1 : M