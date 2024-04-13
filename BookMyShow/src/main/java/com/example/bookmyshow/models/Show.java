package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Show extends BaseModel{
    private Date startTime;
    private Date endTime;
    @ManyToOne
    private Movie movie;
    @OneToMany
    private List<ShowSeat> showseats;

    @ManyToOne
    private Auditorium auditorium;

    @Enumerated(EnumType.STRING)
    private Language language;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    List<ShowFeature> showFeatures;
}

//Show : ShowSeat => 1 : M
//Show : Movie => M : 1
//show : Auditorium => M : 1