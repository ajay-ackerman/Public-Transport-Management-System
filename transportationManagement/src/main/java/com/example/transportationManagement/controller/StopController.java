package com.example.transportationManagement.controller;

import com.example.transportationManagement.entity.Stop;
import com.example.transportationManagement.service.StopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/stop")
@RequiredArgsConstructor
public class StopController {
    private final StopService stopService;

    @PostMapping
    public ResponseEntity<Stop> createStop(@RequestBody Stop stop){
        return ResponseEntity.ok(stopService.createStop(stop));
    }

    @GetMapping
    public ResponseEntity<List<Stop>> getAllStops(){
        return ResponseEntity.ok(stopService.getAllStops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stop> getStopById(@PathVariable Long id){
        return ResponseEntity.ok(stopService.getStopById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Stop> deleteStopById(@PathVariable Long id){
        stopService.deleteStop(id);
        return ResponseEntity.noContent().build();}

}
