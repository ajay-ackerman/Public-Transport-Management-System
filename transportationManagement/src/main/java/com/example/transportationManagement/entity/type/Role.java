package com.example.transportationManagement.entity.type;

public enum Role {
    ADMIN,        // Full control: routes, schedules, vehicles, users, fleet management
    DRIVER,       // Start/end trips, update location
    PASSENGER
}
