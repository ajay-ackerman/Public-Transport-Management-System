package com.example.transportationManagement.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteStopResponseDto {
    private Long id;
    private Long stopId;
    private String stopName;
    private int stopOrder;
    private int arrivalOffsetMinutes;
}
