package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.TicketHistoryDto;
import com.example.transportationManagement.dto.TicketRequestDto;
import com.example.transportationManagement.dto.TicketResponseDto;
import com.example.transportationManagement.entity.Ticket;
import com.example.transportationManagement.entity.Trip;
import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.entity.type.TicketStatus;
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
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;

    public TicketResponseDto bookTicket(TicketRequestDto dto){
        User passenger = userRepository.findById(dto.getPassengerId()).orElseThrow(()->new IllegalArgumentException("User not found...!!"));
        Trip trip = tripRepository.findById(dto.getTripId()).orElseThrow(()->new IllegalArgumentException("Trip not found...!"));
        Ticket ticket = Ticket.builder()
                .passenger(passenger)
                .trip(trip)
                .seatNo(dto.getSeatNo())
                .fareAmount(dto.getFareAmount())
                .ticketStatus(TicketStatus.BOOKED)
                .bookedAt(LocalDateTime.now())
                .build();

         ticket =ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketResponseDto.class );
    }

    @Transactional
    public void cancelTicket(Long ticketID){
        Ticket ticket =ticketRepository.findById(ticketID).orElseThrow(()->new IllegalArgumentException("Ticket With ID doesn't exist...!"));
        ticket.setTicketStatus(TicketStatus.CANCELLED);
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
