package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Theatre extends BaseModel{
    String name;
    String address;

    @OneToMany(fetch = FetchType.EAGER)
    List<Auditorium> auditoriums;

}

//Theatre : Screen
// 1 : M