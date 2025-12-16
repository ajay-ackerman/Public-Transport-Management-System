package com.example.transportationManagement.entity;

import com.example.transportationManagement.entity.type.TripStatus;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Boolean isScheduled;

    private LocalTime scheduledStart;
    private LocalTime scheduledEnd;

    private LocalTime actualStart;
    private LocalTime actualEnd;

    @Enumerated(EnumType.STRING)
    private TripStatus status;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();
}