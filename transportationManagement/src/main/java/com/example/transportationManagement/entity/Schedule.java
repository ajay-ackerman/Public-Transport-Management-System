package com.example.transportationManagement.entity;


import com.example.transportationManagement.entity.type.DayOfWeek;
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


    @JoinColumn(name = "day_of_week_id")
    private DayOfWeek dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    private LocalTime departureTime;
    private LocalTime arrivalTime;

}
