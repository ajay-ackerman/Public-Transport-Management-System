package com.example.transportationManagement.security;

import com.example.transportationManagement.entity.type.PermissionType;
import com.example.transportationManagement.entity.type.Role;

import java.util.*;

public class RolePermissionMapping {

    private static final Map<Role, Set<PermissionType>> ROLE_PERMISSIONS = new HashMap<>();

    static {
        ROLE_PERMISSIONS.put(Role.ADMIN, EnumSet.allOf(PermissionType.class));

        // DRIVER — Trip and Vehicle actions only
        ROLE_PERMISSIONS.put(Role.DRIVER, EnumSet.of(
                PermissionType.TRIP_START,
                PermissionType.TRIP_END,
                PermissionType.TRIP_VIEW,
                    PermissionType.TRIP_CREATE,
                PermissionType.TRIP_CANCEL,

                PermissionType.VEHICLE_VIEW,
                PermissionType.ROUTE_VIEW,
                PermissionType.SCHEDULE_VIEW
        ));

        // PASSENGER — Booking & viewing
        ROLE_PERMISSIONS.put(Role.PASSENGER, EnumSet.of(
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
