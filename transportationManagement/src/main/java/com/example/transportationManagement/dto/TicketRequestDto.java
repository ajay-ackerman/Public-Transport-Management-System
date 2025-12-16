package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.Seat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class    TicketRequestDto {
    private Long passengerId;
    private Long tripId;
    private List<Long> seatIds;
    private Double fareAmount;
}
