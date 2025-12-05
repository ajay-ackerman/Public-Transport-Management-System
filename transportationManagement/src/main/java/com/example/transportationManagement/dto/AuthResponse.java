package com.example.transportationManagement.dto;


import com.example.transportationManagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private UserDto user;
    private String refreshToken;
}
