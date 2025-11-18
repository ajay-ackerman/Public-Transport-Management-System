package com.example.transportationManagement.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketRequestDto {
    private Long passengerId;
    private Long tripId;
    private String seatNo;
    private Double fareAmount;
    private LocalDateTime bookedAt;
}
