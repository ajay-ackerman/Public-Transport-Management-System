package com.example.transportationManagement.service;


import com.example.transportationManagement.dto.VehicleResponseDto;
import com.example.transportationManagement.entity.Vehicle;
import com.example.transportationManagement.repository.VehicleRespository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {
    final  private VehicleRespository vehicleRespository;
    private final ModelMapper modelMapper;

    public VehicleResponseDto addVehicle(Vehicle vehicle){
        Vehicle vehicle1= vehicleRespository .save((vehicle));
        return modelMapper.map(vehicle1, VehicleResponseDto.class);
    }

    public VehicleResponseDto updateVehicle(Long vehicleId , Vehicle vehicle){
        Vehicle existing = vehicleRespository.findById(vehicleId).orElseThrow(()->new IllegalArgumentException("vehicle not found...!"));
        existing.setVehicleNo(vehicle.getVehicleNo());
        existing.setVehicleType(vehicle.getVehicleType());
        existing.setCapacity(vehicle.getCapacity());
        existing.setVehicleStatus(vehicle.getVehicleStatus());
        existing.setCurrentLatitude(vehicle.getCurrentLatitude());
        existing.setCurrentLongitude(vehicle.getCurrentLongitude());

        Vehicle vehicle1= vehicleRespository.save(existing);
        return modelMapper.map(vehicle1, VehicleResponseDto.class);
    }

    public List<VehicleResponseDto> getAllVehicle(){
        return vehicleRespository.findAll().stream().map(vehicle -> modelMapper.map(vehicle, VehicleResponseDto.class))
                .collect(Collectors.toList());
    }

    public VehicleResponseDto getVehicle(Long id){
        Vehicle vehicle= vehicleRespository.findById(id).orElseThrow(()->new IllegalArgumentException("Vehicle not found...!"));
        return modelMapper.map(vehicle, VehicleResponseDto.class);
    }
    public void deleteVehicle(Long id)
    {
        vehicleRespository.deleteById(id);
    }
}

