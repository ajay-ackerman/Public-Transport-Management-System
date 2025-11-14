package com.example.transportationManagement.dto;

import lombok.Data;

@Data
public class TicketRequestDto {
    private Long passengerId;
    private Long tripId;
    private String seatNo;
    private Double bookedAt;
}
