package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.type.DayOfWeek;
import lombok.*;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleRequestDto {
    private DayOfWeek dayOfWeek;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
}
