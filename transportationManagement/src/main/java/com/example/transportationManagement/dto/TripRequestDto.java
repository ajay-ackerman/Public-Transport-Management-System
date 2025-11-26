package com.example.transportationManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data@AllArgsConstructor
@NoArgsConstructor
public class TripRequestDto
{
    private Long vehicleId;
    private Long driverId;
    private Long scheduleId;
    private String Source;
    private String destination;
    private LocalDate tripDate;
}
