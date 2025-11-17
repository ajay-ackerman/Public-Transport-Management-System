package com.example.transportationManagement.controller;


import com.example.transportationManagement.entity.Trip;
import com.example.transportationManagement.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public Trip create(@RequestBody Trip trip) {
        return tripService.createTrip(trip);
    }

    @PutMapping("/{id}/start")
    public Trip start(@PathVariable Long id) {
        return tripService.startTrip(id);
    }

    @PutMapping("/{id}/end")
    public Trip end(@PathVariable Long id) {
        return tripService.endTrip(id);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<Trip> getVehicleTrips(@PathVariable Long vehicleId) {
        return tripService.getVehicleTrip(vehicleId);
    }
}