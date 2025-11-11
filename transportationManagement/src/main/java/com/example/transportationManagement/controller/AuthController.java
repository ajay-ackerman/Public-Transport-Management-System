package com.example.transportationManagement.controller;



import com.example.transportationManagement.dto.AuthRequest;
import com.example.transportationManagement.dto.AuthResponse;
import com.example.transportationManagement.dto.SignupRequest;
import com.example.transportationManagement.dto.SignupResponse;
import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.repository.UserRepository;
import com.example.transportationManagement.security.AuthUtil;
import com.example.transportationManagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final AuthService authService;

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<SignupResponse> register(@RequestBody SignupRequest request) {
        return  ResponseEntity.ok(authService.signup(request));

    }

    // ✅ Login & generate JWT
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    // ✅ Get current use;r details
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(user);
    }
}