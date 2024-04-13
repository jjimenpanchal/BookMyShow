package com.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookTicketRequestDto {
    private Long userId;
    private Long showId;
    private List<Long> ShowSeatIds;
}
