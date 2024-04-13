package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Actor extends BaseModel{
    String Name;
    @ManyToMany(mappedBy = "actors")
    List<Movie> movies;
}

//Actor : Movie => M : M
//1: M
//M : 1