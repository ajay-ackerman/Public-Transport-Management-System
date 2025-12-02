package com.example.transportationManagement.controller;

import com.example.transportationManagement.dto.TicketHistoryDto;
import com.example.transportationManagement.dto.TicketRequestDto;
import com.example.transportationManagement.dto.TicketResponseDto;
import com.example.transportationManagement.service.TicketService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketControlller {
    private final TicketService ticketService;

    @PreAuthorize("hasAuthority('TICKET_BOOK')")
    @PostMapping("/book")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketRequestDto dto){
        return ResponseEntity.ok(ticketService.bookTicket(dto));
    }

    @PreAuthorize("hasAuthority('TICKET_CANCEL')")
    @PostMapping("/{id}/cancel")
    public ResponseEntity<String> cancelTicket(@PathVariable Long ticketId){
        ticketService.cancelTicket(ticketId);
        return ResponseEntity.ok("Ticket Cancelled Successfully..!");
    }

    @PreAuthorize("hasAuthority('TICKET_VIEW')")
    @GetMapping("history/{passengerId}")
    public ResponseEntity<List<TicketHistoryDto>> getTicketHistory(@PathVariable Long passengerId){
        return ResponseEntity.ok(ticketService.getTicketHistory(passengerId));
    }
}

