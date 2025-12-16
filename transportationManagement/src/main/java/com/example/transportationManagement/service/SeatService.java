package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.SeatResponseDto;
import com.example.transportationManagement.entity.Seat;
import com.example.transportationManagement.entity.Trip;
import com.example.transportationManagement.entity.type.SeatStatus;
import com.example.transportationManagement.repository.SeatRepository;
import com.example.transportationManagement.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;

    /**
     * Create seats for a trip (called once when trip is created)
     */
    @Transactional
    public void createSeatsForTrip(Long tripId, int totalSeats) {

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        // Prevent duplicate seat generation
        if (!seatRepository.findByTripId(tripId).isEmpty()) {
            throw new IllegalStateException("Seats already initialized for this trip");
        }

        for (int i = 1; i <= totalSeats; i++) {
            Seat seat = Seat.builder()
                    .seatNumber(i)
                    .status(SeatStatus.AVAILABLE)
                    .trip(trip)
                    .build();

            seatRepository.save(seat);
        }
    }

    /**
     * Get all seats for a trip
     */
    @Transactional(readOnly = true)
    public List<SeatResponseDto> getSeatsByTrip(Long tripId) {
        List<Seat> seats = seatRepository.findByTripId(tripId);
        return seats.stream().map(seat -> modelMapper.map(seat, SeatResponseDto.class)).toList();
    }

    /**
     * Get only available seats
     */
    @Transactional(readOnly = true)
    public List<SeatResponseDto> getAvailableSeats(Long tripId) {
        List<Seat> seats = seatRepository.findByTripIdAndStatus(tripId, SeatStatus.AVAILABLE);
        return seats.stream().map(seat -> modelMapper.map(seat, SeatResponseDto.class)).toList();
    }
}