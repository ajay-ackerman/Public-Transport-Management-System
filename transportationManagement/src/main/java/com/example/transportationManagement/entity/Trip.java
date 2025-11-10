package com.example.transportationManagement.entity;

import com.example.transportationManagement.entity.type.TripStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private User driver;

    private LocalDate date;

    private LocalDateTime scheduledStart;
    private LocalDateTime scheduledEnd;

    @Enumerated(EnumType.STRING)
    private TripStatus status; // PLANNED, ONGOING, COMPLETED, DELAYED, CANCELLED

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();


}

