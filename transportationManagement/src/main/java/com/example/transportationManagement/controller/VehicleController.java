package com.example.transportationManagement.controller;

import com.example.transportationManagement.entity.Vehicle;
import com.example.transportationManagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping
    public List<Vehicle> list() {
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/{id}")
    public Vehicle get(@PathVariable Long id) {
        return vehicleService.getVehicle(id);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}