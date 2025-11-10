package com.example.transportationManagement.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
}
