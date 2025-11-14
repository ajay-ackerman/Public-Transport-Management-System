package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.type.TicketStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponseDto {
    private Long id;
    private String passengerName;
    private Long tripId;
    private String seaNo;
    private Double fareAmount;
    private TicketStatus ticketStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime bookedAt;
}
