package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.TicketRequestDto;
import com.example.transportationManagement.dto.TicketResponseDto;
import com.example.transportationManagement.entity.Trip;
import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.repository.TicketRepository;
import com.example.transportationManagement.repository.TripRepository;
import com.example.transportationManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;

//    public TicketResponseDto bookTicket(TicketRequestDto dto){
//        User passenger = userRepository.findById(dto.getPassengerId()).orElseThrow(()->new IllegalArgumentException("User not found...!!"));
//        Trip trip = tripRepository.findById(dto.getTripId()).orElseThrow(()->new IllegalArgumentException("Trip not found...!"));
//
//    }
}
