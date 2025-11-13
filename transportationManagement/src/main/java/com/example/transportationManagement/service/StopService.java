package com.example.transportationManagement.service;

import com.example.transportationManagement.entity.Stop;
import com.example.transportationManagement.repository.StopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StopService {
    private final StopRepository stopRepository;

    public Stop createStop(Stop stop){
        return stopRepository.save(stop);
    }

    public List<Stop> getAllStops(){
        return stopRepository.findAll();
    }

    public Stop getStopById(Long id){
        return stopRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Stop not found with id: "+ id));
    }

     public void deleteStop(Long id){
         stopRepository.deleteById(id);
     }
}
