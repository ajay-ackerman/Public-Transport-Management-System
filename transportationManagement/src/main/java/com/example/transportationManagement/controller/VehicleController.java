package com.example.transportationManagement.controller;

import com.example.transportationManagement.entity.Vehicle;
import com.example.transportationManagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PreAuthorize("hasAuthority('VEHICLE_CREATE')")
    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PreAuthorize("hasAuthority('VEHICLE_VIEW')")
    @GetMapping
    public List<Vehicle> list() {
        return vehicleService.getAllVehicle();
    }

    @PreAuthorize("hasAuthority('VEHICLE_VIEW')")
    @GetMapping("/{id}")
    public Vehicle get(@PathVariable Long id) {
        return vehicleService.getVehicle(id);
    }

    @PreAuthorize("hasAuthority('VEHICLE_UPDATE')")
    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @PreAuthorize("hasAuthority('VEHICLE_DELETE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}