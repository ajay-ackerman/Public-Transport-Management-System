package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.UserDto;
import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.entity.type.Role;
import com.example.transportationManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
    public List<UserDto> getDrivers() {
        List<User> users = userRepository.findAllByRole(Role.DRIVER);
        return users.stream().map((user) ->
                        modelMapper.map(user, UserDto.class))
                       .collect(Collectors.toList());
    }
}