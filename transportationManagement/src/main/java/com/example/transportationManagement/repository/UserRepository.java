package com.example.transportationManagement.repository;

import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.entity.type.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);

    List<User> findAllByRole(Role role);
}