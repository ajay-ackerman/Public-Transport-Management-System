package com.example.transportationManagement.controller;

import com.example.transportationManagement.entity.Stop;
import com.example.transportationManagement.service.StopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/stop")
@RequiredArgsConstructor
public class StopController {
    private final StopService stopService;

    @PreAuthorize("hasAuthority('STOP_CREATE')")
    @PostMapping
    public ResponseEntity<Stop> createStop(@RequestBody Stop stop){
        return ResponseEntity.ok(stopService.createStop(stop));
    }

    @PreAuthorize("hasAuthority('STOP_VIEW')")
    @GetMapping
    public ResponseEntity<List<Stop>> getAllStops(){
        return ResponseEntity.ok(stopService.getAllStops());
    }

    @PreAuthorize("hasAuthority('STOP_VIEW')")
    @GetMapping("/{id}")
    public ResponseEntity<Stop> getStopById(@PathVariable Long id){
        return ResponseEntity.ok(stopService.getStopById(id));
    }
    @PreAuthorize("hasAuthority('STOP_DELETE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Stop> deleteStopById(@PathVariable Long id){
        stopService.deleteStop(id);
        return ResponseEntity.noContent().build();}

}
