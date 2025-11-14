package com.example.transportationManagement.repository;

import com.example.transportationManagement.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public Optional<Ticket> findByPassengerId(Long passengerId);
}
