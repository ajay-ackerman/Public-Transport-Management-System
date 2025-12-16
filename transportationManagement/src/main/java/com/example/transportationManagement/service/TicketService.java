package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.*;
import com.example.transportationManagement.entity.Seat;
import com.example.transportationManagement.entity.Ticket;
import com.example.transportationManagement.entity.Trip;
import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.entity.type.SeatStatus;
import com.example.transportationManagement.entity.type.TicketStatus;
import com.example.transportationManagement.repository.SeatRepository;
import com.example.transportationManagement.repository.TicketRepository;
import com.example.transportationManagement.repository.TripRepository;
import com.example.transportationManagement.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;
    private final SeatRepository seatRepository;

    @Transactional
    public TicketResponseDto bookTicket(TicketRequestDto dto) {
        User currentUser = userRepository.findById(dto.getPassengerId()).orElseThrow();
        Trip trip = tripRepository.findById(dto.getTripId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        List<Seat> seats = seatRepository.lockAvailableSeats(
                dto.getSeatIds(), dto.getTripId()
        );

        if (seats.size() != dto.getSeatIds().size()) {
            throw new IllegalStateException("One or more seats already booked");
        }

        Ticket ticket = Ticket.builder()
                .trip(trip)
                .passenger(currentUser)
                .status(TicketStatus.BOOKED)
                .bookedAt(LocalDateTime.now())
                .build();

        ticketRepository.save(ticket);

        seats.forEach(seat -> {
            seat.setStatus(SeatStatus.BOOKED);
        });

        seatRepository.saveAll(seats);

        ticket.setSeats(seats);

        return modelMapper.map(ticket, TicketResponseDto.class);
    }

    @Transactional
    public void cancelTicket(Long ticketID){
        Ticket ticket =ticketRepository.findById(ticketID).orElseThrow(()->new IllegalArgumentException("Ticket With ID doesn't exist...!"));
        ticket.setStatus(TicketStatus.CANCELLED);
    }

    public List<TicketHistoryDto> getTicketHistory(Long passengerId){
        return ticketRepository.findByPassengerId(passengerId)
                .stream()
                .map(
                        t->modelMapper.map(t , TicketHistoryDto.class)
                )
                .collect(Collectors.toList());
    }

}
