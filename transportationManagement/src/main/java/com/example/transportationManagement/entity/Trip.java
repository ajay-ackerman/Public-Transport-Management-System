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
@ToString
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "route_id")
    private Route route;

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

    private LocalTime scheduledStart;
    private LocalTime scheduledEnd;

    private LocalTime actualStart;
    private LocalTime actualEnd;

    @Enumerated(EnumType.STRING)
    private TripStatus status = TripStatus.SCHEDULED; // PLANNED, ONGOING, COMPLETED, DELAYED, CANCELLED

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

}

