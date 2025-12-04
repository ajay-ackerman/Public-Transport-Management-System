package com.example.transportationManagement.controller;

import com.example.transportationManagement.dto.UserDto;
import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final CustomUserDetailsService customUserDetailsService;
    @GetMapping("/drivers")
    public ResponseEntity<List<UserDto>> getAlDrivers(){
                return ResponseEntity.ok(customUserDetailsService.getDrivers());
            }
}