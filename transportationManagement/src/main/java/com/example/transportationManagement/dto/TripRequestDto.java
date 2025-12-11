package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.Route;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripRequestDto
{
    private Long vehicleId;
    private Long driverId;
    private Boolean isScheduled;
    private Long scheduleId;
    private String Source;
    private String destination;
    private LocalDate tripDate;
    private LocalTime scheduledStart;
    private LocalTime scheduledEnd;
    private Long routeId;
}
