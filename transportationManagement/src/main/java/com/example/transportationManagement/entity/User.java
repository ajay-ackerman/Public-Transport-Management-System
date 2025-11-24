package com.example.transportationManagement.entity;
import com.example.transportationManagement.entity.type.PermissionType;
import com.example.transportationManagement.entity.type.Role;
import com.example.transportationManagement.security.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@ToString
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String phone;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role.name()));

        Set<PermissionType> permissions = RolePermissionMapping.getPermissions(this.role);

        permissions.forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.name()))
        );

        return authorities;
    }

    @Override
    public String getUsername() {
        return this.name;
    }


}