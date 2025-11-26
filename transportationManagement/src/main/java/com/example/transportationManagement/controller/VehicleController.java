package com.example.transportationManagement.controller;

import com.example.transportationManagement.dto.VehicleResponseDto;
import com.example.transportationManagement.entity.Vehicle;
import com.example.transportationManagement.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<VehicleResponseDto> create(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.addVehicle(vehicle));
    }

    @PreAuthorize("hasAuthority('VEHICLE_VIEW')")
    @GetMapping
    public ResponseEntity<List<VehicleResponseDto>> list() {
        return ResponseEntity.ok(vehicleService.getAllVehicle());
    }

    @PreAuthorize("hasAuthority('VEHICLE_VIEW')")
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }

    @PreAuthorize("hasAuthority('VEHICLE_UPDATE')")
    @PutMapping("/{id}")
    public ResponseEntity<VehicleResponseDto> update(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicle));
    }

    @PreAuthorize("hasAuthority('VEHICLE_DELETE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}