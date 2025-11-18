package com.example.transportationManagement.controller;


import com.example.transportationManagement.dto.TripRequestDto;
import com.example.transportationManagement.dto.TripResponseDto;
import com.example.transportationManagement.entity.Trip;
import com.example.transportationManagement.service.TripService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripResponseDto> create(@RequestBody TripRequestDto trip) {
        return ResponseEntity.ok(tripService.createTrip(trip));
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<TripResponseDto>  start(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.startTrip(id));
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<TripResponseDto> end(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.endTrip(id));
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<TripResponseDto>> getVehicleTrips(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(tripService.getVehicleTrip(vehicleId));
    }
}