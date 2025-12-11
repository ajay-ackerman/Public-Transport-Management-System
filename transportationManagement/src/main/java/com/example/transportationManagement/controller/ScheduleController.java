package com.example.transportationManagement.controller;

import com.example.transportationManagement.dto.ScheduleRequestDto;
import com.example.transportationManagement.dto.ScheduleResponseDto;
import com.example.transportationManagement.service.RouteService;
import com.example.transportationManagement.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final RouteService routeService;
    private final ScheduleService scheduleService;

    @PreAuthorize("hasAuthority('SCHEDULE_CREATE')")
    @PostMapping("/{routeId}")
    public  ResponseEntity<ScheduleResponseDto> addScheduleToRoute(@PathVariable  Long routeId , @RequestBody ScheduleRequestDto schedule){
        return ResponseEntity.ok( routeService.addScheduleToRoute(routeId,schedule));
    }

    @PreAuthorize("hasAuthority('SCHEDULE_VIEW')")
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(){
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @PreAuthorize("hasAuthority('SCHEDULE_VIEW')")
    @GetMapping("/{routeId}")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(@PathVariable Long routeId){
        return ResponseEntity.ok(routeService.getSchedules(routeId));
    }
}
