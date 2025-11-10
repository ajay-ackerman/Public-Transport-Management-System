package com.example.transportationManagement.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vehicleNo;

    private String vehicleType;

    private int capacity;

    @OneToMany(mappedBy = "vehicle")
    private List<Trip> trips = new ArrayList<>();

}
