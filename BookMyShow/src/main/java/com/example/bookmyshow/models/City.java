package com.example.bookmyshow.models;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Getter
@Setter
public class City extends BaseModel{
    private String name;
    private Long pincode;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Theatre> theatres;

}

//City : Theatre
//    1 : M