package com.example.transportationManagement.security;

import com.example.transportationManagement.entity.type.PermissionType;
import com.example.transportationManagement.entity.type.Role;
import com.example.transportationManagement.entity.type.RoleType;

import java.util.*;

public class RolePermissionMapping {

    private static final Map<RoleType, Set<PermissionType>> ROLE_PERMISSIONS = new HashMap<>();

    static {
        ROLE_PERMISSIONS.put(RoleType.ADMIN, EnumSet.allOf(PermissionType.class));

        // MANAGER — Manage vehicles, routes, schedules, trips
        ROLE_PERMISSIONS.put(RoleType.MANAGER, EnumSet.of(
                PermissionType.ROUTE_CREATE,
                PermissionType.ROUTE_UPDATE,
                PermissionType.ROUTE_VIEW,

                PermissionType.STOP_CREATE,
                PermissionType.STOP_UPDATE,
                PermissionType.STOP_VIEW,

                PermissionType.SCHEDULE_CREATE,
                PermissionType.SCHEDULE_UPDATE,
                PermissionType.SCHEDULE_VIEW,

                PermissionType.VEHICLE_CREATE,
                PermissionType.VEHICLE_UPDATE,
                PermissionType.VEHICLE_VIEW,

                PermissionType.TRIP_CREATE,
                PermissionType.TRIP_VIEW,

                PermissionType.USER_VIEW
        ));

        // DRIVER — Trip and Vehicle actions only
        ROLE_PERMISSIONS.put(RoleType.DRIVER, EnumSet.of(
                PermissionType.TRIP_START,
                PermissionType.TRIP_END,
                PermissionType.TRIP_VIEW,

                PermissionType.VEHICLE_VIEW
        ));

        // PASSENGER — Booking & viewing
        ROLE_PERMISSIONS.put(RoleType.PASSENGER, EnumSet.of(
                PermissionType.TICKET_BOOK,
                PermissionType.TICKET_CANCEL,
                PermissionType.TICKET_VIEW,

                PermissionType.TRIP_VIEW,
                PermissionType.ROUTE_VIEW,
                PermissionType.SCHEDULE_VIEW
        ));
    }

    // Return permissions for a given role
    public static Set<PermissionType> getPermissions(Role role) {
        return ROLE_PERMISSIONS.getOrDefault(role, Collections.emptySet());
    }
}
