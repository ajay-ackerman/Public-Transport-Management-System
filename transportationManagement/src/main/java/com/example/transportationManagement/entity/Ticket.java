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

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    private String seatNo;
    private double fareAmount;

    @Enumerated(EnumType.STRING)
    private TicketStatus status; // BOOKED, CANCELLED, USED

    private LocalDateTime bookedAt = LocalDateTime.now();


}
