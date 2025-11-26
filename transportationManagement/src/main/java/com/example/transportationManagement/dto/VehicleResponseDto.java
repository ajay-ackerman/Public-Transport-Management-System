package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.type.VehicleStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class VehicleResponseDto {
    private Long id;

    private String vehicleNo;

    private String vehicleType;

    private int capacity;

    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    private double currentLatitude;
    private double currentLongitude;

}
