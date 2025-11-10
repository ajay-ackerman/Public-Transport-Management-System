package com.example.transportationManagement.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String transportMode; // BUS / TRAIN

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RouteStop> stops = new ArrayList<>();

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    private boolean active = true;
}
