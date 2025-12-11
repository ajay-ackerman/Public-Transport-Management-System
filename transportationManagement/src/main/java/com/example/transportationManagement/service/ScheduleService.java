package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.ScheduleResponseDto;
import com.example.transportationManagement.entity.Schedule;
import com.example.transportationManagement.repository.RouteRepository;
import com.example.transportationManagement.repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ScheduleService {
    private  final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;
    private final RouteRepository routeRepository;

    public List<ScheduleResponseDto> getAllSchedules() {
        List<Schedule>  schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> {
                    // 1. Map basic fields using ModelMapper
                    ScheduleResponseDto dto = modelMapper.map(schedule, ScheduleResponseDto.class);

                    // 2. Manually set the route name
                    if (schedule.getRoute() != null) {
                        // Assuming Route entity has a getName() method
                        dto.setRouteName(schedule.getRoute().getName());
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }
}
