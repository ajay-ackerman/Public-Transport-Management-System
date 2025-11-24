package com.example.transportationManagement.controller;

import com.example.transportationManagement.entity.RouteStop;
import com.example.transportationManagement.service.RouteStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/routestops")
public class RouteStopController {
    private final RouteStopService routeStopService;

    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    @GetMapping
    public ResponseEntity<List<RouteStop>> getAllRouteStops(){
        return ResponseEntity.ok(routeStopService.getAllRouteStops());
    }

    @PreAuthorize("hasAuthority('ROUTE_VIEW')")
    @GetMapping("/{id}")
    public ResponseEntity<RouteStop> getRouteStopById(@PathVariable Long id) {
        return ResponseEntity.ok(routeStopService.getRouteStopById(id));
    }

    @PreAuthorize("hasAuthority('ROUTE_UPDATE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRouteStopById(@PathVariable Long id) {
        routeStopService.deleteRouteStops(id);
        return ResponseEntity.noContent().build();
    }
}
