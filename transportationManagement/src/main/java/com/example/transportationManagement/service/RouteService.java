package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.RouteResponseDto;
import com.example.transportationManagement.dto.RouteStopResponseDto;
import com.example.transportationManagement.dto.ScheduleRequestDto;
import com.example.transportationManagement.dto.ScheduleResponseDto;
import com.example.transportationManagement.entity.Route;
import com.example.transportationManagement.entity.RouteStop;
import com.example.transportationManagement.entity.Schedule;
import com.example.transportationManagement.entity.Stop;
import com.example.transportationManagement.repository.RouteRepository;
import com.example.transportationManagement.repository.RouteStopRepository;
import com.example.transportationManagement.repository.ScheduleRepository;
import com.example.transportationManagement.repository.StopRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteService {

    private final RouteRepository routeRepository;
    private final RouteStopRepository routeStopRepository;
    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;
    private final StopRepository stopRepository;

    // Create or Update Route
    public RouteResponseDto saveOrUpdateRoute(Route route) {
        Route savedRoute = routeRepository.save(route);
        return convertToDto(savedRoute);
    }

    // Get Route by ID
    public RouteResponseDto getRouteById(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Route not found with id: " + id));
        return convertToDto(route);
    }

    // Delete Route
    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new IllegalArgumentException("Route not found with id: " + id);
        };
        routeRepository.deleteById(id);
    }

    // Get All Routes
    public List<RouteResponseDto> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get Active Routes
    public List<RouteResponseDto> getActiveRoutes() {
        return routeRepository.findByActiveTrue().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get Routes by Transport Mode
    public List<RouteResponseDto> getRoutesByTransportMode(String mode) {
        return routeRepository.findByTransportMode(mode.toUpperCase()).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Add Stop to Route
    public RouteStopResponseDto addStopToRoute(Long routeId, RouteStopResponseDto dto) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found"));
        Stop stop = stopRepository.findById(dto.getStopId())
                .orElseThrow(() -> new EntityNotFoundException("stop not found"));
        RouteStop routeStop=  new RouteStop();
        routeStop.setRoute(route);
        routeStop.setStop(stop);
        routeStop.setArrivalOffsetMinutes(dto.getArrivalOffsetMinutes());
        routeStop.setStopOrder(dto.getStopOrder());
        RouteStop saved = routeStopRepository.save(routeStop);

        return RouteStopResponseDto.builder()
                .id(saved.getId())
                .stopId(stop.getId())
                .stopName(stop.getName())
                .stopOrder(saved.getStopOrder())
                .arrivalOffsetMinutes(saved.getArrivalOffsetMinutes())
                .build();
    }

    // Add Schedule to Route
    public ScheduleResponseDto addScheduleToRoute(Long routeId, ScheduleRequestDto dto) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route not found"));

        Schedule schedule = new Schedule();
        schedule= Schedule.builder()
                .departureTime(dto.getDepartureTime())
                .arrivalTime(dto.getArrivalTime())
                .dayOfWeek(dto.getDayOfWeek())
                .route(route)
                .build();
        scheduleRepository.save(schedule);


        return modelMapper.map(schedule,ScheduleResponseDto.class);
    }

    //Get Schedules in route
    public List<ScheduleResponseDto> getSchedules(Long routeId){
        List<Schedule>  schedules = scheduleRepository.findByRouteId(routeId);
        return schedules.stream()
                .map(schedule -> {
                    return modelMapper.map(schedule, ScheduleResponseDto.class);
                })
                .collect(Collectors.toList());
    }

    // Convert Route → DTO using ModelMapper
    private RouteResponseDto convertToDto(Route route) {
        RouteResponseDto dto = modelMapper.map(route, RouteResponseDto.class);

        // If you want stops as names only:
//        dto.setStops(route.getStops().stream()
//                .map(routeStop -> routeStop.getStops()) // ✅ fetch Stop.name
//                .collect(Collectors.toList()));

        return dto;
    }
}
