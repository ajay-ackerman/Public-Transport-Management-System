package com.example.transportationManagement.service;

import com.example.transportationManagement.entity.Trip;
import com.example.transportationManagement.entity.type.TripStatus;
import com.example.transportationManagement.repository.TripRepository;
import com.example.transportationManagement.repository.VehicleRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final VehicleRespository vehicleRespository;

    public Trip createTrip(Trip trip){
        return tripRepository.save(trip);
    }

    public Trip startTrip(Long id){
        Trip  trip =tripRepository.findById(id).orElseThrow(()->new IllegalArgumentException("trip not found ..!"));
        trip.setTripStatus(TripStatus.ONGOING);
        trip.setActualStart(LocalTime.now());
        return tripRepository.save(trip);
    }

    public Trip endTrip(Long id ){
        Trip  trip =tripRepository.findById(id).orElseThrow(()->new IllegalArgumentException("trip not found ..!"));
        trip.setTripStatus(TripStatus.COMPLETED);
        trip.setActualEnd(LocalTime.now());
        return tripRepository.save(trip);
    }

    public List<Trip> getVehicleTrip(Long vehicleId){
        return tripRepository.findByVehicleId(vehicleId);
    }

}
