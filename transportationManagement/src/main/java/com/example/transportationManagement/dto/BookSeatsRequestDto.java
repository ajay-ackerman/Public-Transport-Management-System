package com.example.transportationManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookSeatsRequestDto {
    private Long tripId;
    private List<Long> seatIds;
}
