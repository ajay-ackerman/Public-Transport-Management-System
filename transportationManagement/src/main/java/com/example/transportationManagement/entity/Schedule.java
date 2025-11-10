package com.example.transportationManagement.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "schedules")@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private LocalTime startTime;
    private LocalTime endTime;

}
