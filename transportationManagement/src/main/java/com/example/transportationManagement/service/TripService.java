package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.TripRequestDto;
import com.example.transportationManagement.dto.TripResponseDto;
import com.example.transportationManagement.entity.*;
import com.example.transportationManagement.entity.type.TripStatus;
import com.example.transportationManagement.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final VehicleRespository vehicleRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RouteRepository routeRepository;
    private final SeatService seatService;
    @Transactional
    public TripResponseDto createTrip(TripRequestDto dto) {
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found"));

        User driver = userRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        Trip trip=new Trip();
        if(dto.getIsScheduled()) {
            Schedule schedule = scheduleRepository.findById(dto.getScheduleId())
                    .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));
             trip = Trip.builder()
                    .route(schedule.getRoute())
                    .isScheduled(true)
                    .schedule(schedule)
                    .vehicle(vehicle)
                    .source(dto.getSource())
                    .destination(dto.getDestination())
                    .driver(driver)
                    .date(dto.getTripDate())
                    .scheduledStart(schedule.getDepartureTime())
                    .scheduledEnd(schedule.getArrivalTime())
                    .status(TripStatus.SCHEDULED)
                    .build();
        }
        else {
            Route route = routeRepository.findById(dto.getRouteId()).orElseThrow();
             trip = Trip.builder()
                    .route(route)
                    .isScheduled(false)
                    .schedule(null)
                    .vehicle(vehicle)
                    .source(dto.getSource())
                    .destination(dto.getDestination())
                    .driver(driver)
                    .date(dto.getTripDate())
                    .scheduledStart(dto.getScheduledStart())
                    .scheduledEnd(dto.getScheduledEnd())
                    .status(TripStatus.SCHEDULED)
                    .build();
        }


        Trip savedTrip = tripRepository.save(trip);

        seatService.createSeatsForTrip(savedTrip.getId(),40);

        return modelMapper.map(savedTrip, TripResponseDto.class);
    }

    @Transactional
    public TripResponseDto startTrip(Long id){
        Trip  trip =tripRepository.findById(id).orElseThrow(()->new IllegalArgumentException("trip not found ..!"));
        trip.setStatus(TripStatus.ONGOING);
        trip.setActualStart(LocalTime.now());

        return modelMapper.map(trip, TripResponseDto.class);
    }

    @Transactional
    public TripResponseDto endTrip(Long id ){
        Trip  trip =tripRepository.findById(id).orElseThrow(()->new IllegalArgumentException("trip not found ..!"));
        trip.setStatus(TripStatus.COMPLETED);
        trip.setActualEnd(LocalTime.now());
        return modelMapper.map(trip, TripResponseDto.class);
    }

    public List<TripResponseDto> getVehicleTrip(Long vehicleId){
        List<Trip >trips=tripRepository.findByVehicleId(vehicleId);
        return trips.stream().map((trip)->modelMapper.map(trip, TripResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<TripResponseDto> searchTrips(String source, String destination, LocalDate date) {
        List<Trip>  trips = tripRepository.findBySourceAndDestinationAndDate(source,destination,date);
        return trips.stream().map((trip)->modelMapper.map(trip, TripResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<TripResponseDto> getAllTrips() {
        List<Trip>  trips = tripRepository.findAll();
        return trips.stream().map((trip)->modelMapper.map(trip, TripResponseDto.class))
                .collect(Collectors.toList());
    }

    public List<TripResponseDto> getDriverTrip(Long driverId) {
        List<Trip >trips=tripRepository.findByDriverId(driverId);
        return trips.stream().map((trip)->modelMapper.map(trip, TripResponseDto.class))
                .collect(Collectors.toList());
    }

    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }

//    private TripResponseDto mapToDTO(Trip trip) {
//        return TripResponseDto.builder()
//                .id(trip.getId())
//
//                .routeId(trip.getRoute().getId())
//                .routeName(trip.getRoute().getName())
//
//                .scheduleId(trip.getSchedule().getId())
//                .departureTime(trip.getSchedule().getDepartureTime().toString())
//                .arrivalTime(trip.getSchedule().getArrivalTime().toString())
//
//                .vehicleId(trip.getVehicle().getId())
//                .vehicleNo(trip.getVehicle().getVehicleNo())
//
//                .driverId(trip.getDriver().getId())
//                .driverName(trip.getDriver().getName())
//
//                .tripDate(trip.getDate().toString())
//                .scheduledStart(trip.getScheduledStart().toString())
//                .scheduledEnd(trip.getScheduledEnd().toString())
//
//                .status(trip.getStatus())
//
//                .bookedSeats(trip.getTickets().size())
//                .build();
//    }
}
