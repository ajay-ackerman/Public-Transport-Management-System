package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteResponseDto {
    private Long id;
    private String name;
    private String transportMode;
    private boolean active;
    private List<RouteStopResponseDto> stops;
    private List<ScheduleResponseDto> schedules;
}