package com.example.transportationManagement.controller;

import com.example.transportationManagement.dto.ScheduleRequestDto;
import com.example.transportationManagement.dto.ScheduleResponseDto;
import com.example.transportationManagement.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    public final RouteService routeService;

    @PostMapping("/{routeId}")
    public  ResponseEntity<ScheduleResponseDto> addScheduleToRoute(@PathVariable  Long routeId , @RequestBody ScheduleRequestDto schedule){
        return ResponseEntity.ok( routeService.addScheduleToRoute(routeId,schedule));
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules(@PathVariable Long routeId){
        return ResponseEntity.ok(routeService.getSchedules(routeId));
    }
}
