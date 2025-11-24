package com.example.transportationManagement.controller;


import com.example.transportationManagement.dto.TripRequestDto;
import com.example.transportationManagement.dto.TripResponseDto;
import com.example.transportationManagement.entity.Trip;
import com.example.transportationManagement.service.TripService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PreAuthorize("hasAuthority('TRIP_CREATE')")
    @PostMapping
    public ResponseEntity<TripResponseDto> create(@RequestBody TripRequestDto trip) {
        return ResponseEntity.ok(tripService.createTrip(trip));
    }

    @PreAuthorize("hasAuthority('TRIP_START')")
    @PutMapping("/{id}/start")
    public ResponseEntity<TripResponseDto>  start(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.startTrip(id));
    }

    @PreAuthorize("hasAuthority('TRIP_END')")
    @PutMapping("/{id}/end")
    public ResponseEntity<TripResponseDto> end(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.endTrip(id));
    }

    @PreAuthorize("hasAuthority('TRIP_VIEW')")
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<TripResponseDto>> getVehicleTrips(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(tripService.getVehicleTrip(vehicleId));
    }
}