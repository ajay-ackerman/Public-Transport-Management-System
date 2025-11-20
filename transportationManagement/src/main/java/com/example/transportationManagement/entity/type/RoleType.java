package com.example.transportationManagement.entity.type;

public enum RoleType {
    ADMIN,        // Full control: routes, schedules, vehicles, users
    MANAGER,      // Manage fleet, schedules, trips
    DRIVER,       // Start/end trips, update location
    PASSENGER     // Book tickets, view trips & history
}
