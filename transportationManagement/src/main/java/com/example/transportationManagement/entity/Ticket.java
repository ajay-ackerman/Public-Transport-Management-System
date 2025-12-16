package com.example.transportationManagement.entity;

import com.example.transportationManagement.entity.type.TicketStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private User passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToMany
    @JoinTable(name = "ticket_seats",
        joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id"),
            uniqueConstraints = {
            @UniqueConstraint(columnNames = {"seat_id"})
            }
    )
    private List<Seat> seats = new ArrayList<>();

    private double fareAmount;

    @Enumerated(EnumType.STRING)
    private TicketStatus status; // BOOKED, CANCELLED, USED

    private LocalDateTime bookedAt;
}

