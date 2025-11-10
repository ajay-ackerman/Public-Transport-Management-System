package com.example.transportationManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor@Data
public class AuthResponse {
    private String token;
    private String message;
}
