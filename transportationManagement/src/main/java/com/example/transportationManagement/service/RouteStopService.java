package com.example.transportationManagement.service;

import com.example.transportationManagement.entity.RouteStop;
import com.example.transportationManagement.repository.RouteRepository;
import com.example.transportationManagement.repository.RouteStopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RouteStopService {
    private final RouteRepository routeRepository;
    private final RouteStopRepository routeStopRepository;

    public List<RouteStop> getAllRouteStops(){
        return routeStopRepository.findAll();
    }

    public  RouteStop getRouteStopById(Long id){
        return routeStopRepository.findById(id).orElseThrow(()->new IllegalArgumentException("RouteStop with this id Doesn't Exist"));

    }

    public void  deleteRouteStops(Long id){
        routeStopRepository.deleteById(id);
    }

}
