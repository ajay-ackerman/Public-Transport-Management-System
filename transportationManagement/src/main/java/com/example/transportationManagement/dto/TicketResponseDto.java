package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.type.TicketStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponseDto {
    private Long ticketId;
    private String passengerName;
    private String source;
    private String destination;
    private LocalDate date;
    private String vehicleNo;
    private Double fareAmount;
    private List<Integer> seatNumbers;
    private TicketStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime bookedAt;
}
