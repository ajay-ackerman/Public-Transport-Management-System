package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.type.TicketStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TicketHistoryDto {
    private Long id;
    private String seatNo;
    private Double fareAmount;
    private TicketStatus ticketStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime bookedAt;

}
