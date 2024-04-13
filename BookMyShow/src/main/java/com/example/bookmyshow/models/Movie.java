package com.example.bookmyshow.models;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.List;

@Entity
public class Movie extends BaseModel{
    String name;
    Time duration;
    double rating;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    List<MovieFeature> features;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    List<Language>languages;

    @ManyToMany
    List<Actor> actors;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    List<Genre> genres;

}
