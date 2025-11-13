package com.example.transportationManagement.controller;

import com.example.transportationManagement.entity.RouteStop;
import com.example.transportationManagement.service.RouteStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/routestops")
public class RouteStopController {
    private final RouteStopService routeStopService;

    @GetMapping
    public ResponseEntity<List<RouteStop>> getAllRouteStops(){
        return ResponseEntity.ok(routeStopService.getAllRouteStops());

    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteStop> getRouteStopById(@PathVariable Long id){
        return ResponseEntity.ok(routeStopService.getRouteStopById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteRouteStopbyId(@PathVariable Long id ){
        routeStopService.deleteRouteStops(id);
        ResponseEntity.noContent().build();
    }
}
