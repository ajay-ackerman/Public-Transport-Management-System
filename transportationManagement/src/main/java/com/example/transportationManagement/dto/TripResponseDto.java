package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.type.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripResponseDto {

    private Long id;

    private Long routeId;
    private String routeName;

    private Long scheduleId;
    private String departureTime;
    private String arrivalTime;

    private Long vehicleId;
    private String vehicleNo;

    private Long driverId;
    private String driverName;

    private String tripDate;
    private String scheduledStart;
    private String scheduledEnd;

    private TripStatus status;

    private int bookedSeats; // tickets.size()
}