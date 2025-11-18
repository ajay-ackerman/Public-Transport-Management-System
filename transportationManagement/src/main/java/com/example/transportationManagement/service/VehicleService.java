package com.example.transportationManagement.service;


import com.example.transportationManagement.entity.Vehicle;
import com.example.transportationManagement.repository.VehicleRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    final  private VehicleRespository vehicleRespository;

    public Vehicle addVehicle(Vehicle vehicle){
        return vehicleRespository .save((vehicle));
    }

    public Vehicle updateVehicle(Long vehicleId , Vehicle vehicle){
        Vehicle existing = vehicleRespository.findById(vehicleId).orElseThrow(()->new IllegalArgumentException("vehicle not found...!"));
        existing.setVehicleNo(vehicle.getVehicleNo());
        existing.setVehicleType(vehicle.getVehicleType());
        existing.setCapacity(vehicle.getCapacity());
        existing.setVehicleStatus(vehicle.getVehicleStatus());
        existing.setCurrentLatitude(vehicle.getCurrentLatitude());
        existing.setCurrentLongitude(vehicle.getCurrentLongitude());

        return vehicleRespository.save(existing);
    }

    public List<Vehicle> getAllVehicle(){
        return vehicleRespository.findAll();
    }

    public Vehicle getVehicle(Long id){
        return vehicleRespository.findById(id).orElseThrow(()->new IllegalArgumentException("Vehicle not found...!"));

    }
    public void deleteVehicle(Long id)
    {
        vehicleRespository.deleteById(id);
    }
}

