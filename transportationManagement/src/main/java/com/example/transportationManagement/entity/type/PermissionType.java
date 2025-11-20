package com.example.transportationManagement.entity.type;

public enum PermissionType {

    // ROUTE
    ROUTE_CREATE,
    ROUTE_UPDATE,
    ROUTE_DELETE,
    ROUTE_VIEW,

    // STOPS
    STOP_CREATE,
    STOP_UPDATE,
    STOP_DELETE,
    STOP_VIEW,

    // SCHEDULES
    SCHEDULE_CREATE,
    SCHEDULE_UPDATE,
    SCHEDULE_DELETE,
    SCHEDULE_VIEW,

    // VEHICLES
    VEHICLE_CREATE,
    VEHICLE_UPDATE,
    VEHICLE_DELETE,
    VEHICLE_VIEW,

    // TRIPS
    TRIP_CREATE,
    TRIP_START,
    TRIP_END,
    TRIP_CANCEL,
    TRIP_VIEW,

    // TICKETS
    TICKET_BOOK,
    TICKET_CANCEL,
    TICKET_VIEW,

    // USERS
    USER_CREATE,
    USER_UPDATE,
    USER_DELETE,
    USER_VIEW
}
