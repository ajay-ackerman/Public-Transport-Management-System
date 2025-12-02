package com.example.transportationManagement.dto;

import com.example.transportationManagement.entity.type.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private Role role;

    private String phone;
}
