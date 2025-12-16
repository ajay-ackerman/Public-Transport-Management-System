package com.example.transportationManagement.controller;

import com.example.transportationManagement.dto.SeatResponseDto;
import com.example.transportationManagement.entity.Seat;
import com.example.transportationManagement.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    /**
     * Initialize seats for a trip (ADMIN)
     */
    @PostMapping("/init/{tripId}")
    @PreAuthorize("hasAuthority('SEAT_CREATE')")
    public ResponseEntity<Void> initSeats(
            @PathVariable Long tripId,
            @RequestParam int totalSeats
    ) {
        seatService.createSeatsForTrip(tripId, totalSeats);
        return ResponseEntity.ok().build();
    }

    /**
     * Get all seats of a trip
     */
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<SeatResponseDto>> getSeatsByTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(seatService.getSeatsByTrip(tripId));
    }

    /**
     * Get available seats for booking
     */
    @GetMapping("/trip/{tripId}/available")
    public ResponseEntity<List<SeatResponseDto>> getAvailableSeats(@PathVariable Long tripId) {
        return ResponseEntity.ok(seatService.getAvailableSeats(tripId));
    }
}