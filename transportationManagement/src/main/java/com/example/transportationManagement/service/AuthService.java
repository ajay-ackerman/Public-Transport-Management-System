package com.example.transportationManagement.service;

import com.example.transportationManagement.dto.*;
import com.example.transportationManagement.entity.User;
import com.example.transportationManagement.entity.type.Role;
import com.example.transportationManagement.repository.UserRepository;
import com.example.transportationManagement.security.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private  final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final ModelMapper modelMapper;
    public AuthResponse login(AuthRequest loginRequestDto) {

        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );
        User user= (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        UserDto user1 = modelMapper.map(user, UserDto.class);
        return new AuthResponse(token,user1);
    }

    public User signUpInternal(SignupRequest signupRequestDto){

        User user=  userRepository.findByEmail(signupRequestDto.getEmail()).orElse(null);

        if(user != null) {
            throw new BadCredentialsException("user already exist..!!");
        }

        user = User.builder()
                .name(signupRequestDto.getName())
                .email(signupRequestDto.getEmail())
                .role(Role.valueOf(signupRequestDto.getRole()))
                .phone(signupRequestDto.getPhone())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build();


        user = userRepository.saveAndFlush(user);

//        if(authProviderType == AuthProviderType.EMAIL){
//            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
//        }
        return user;
    }

    public SignupResponse signup(SignupRequest signupRequestDto) {
        User user=  signUpInternal(signupRequestDto);

        return new SignupResponse(user.getId(),user.getUsername());
    }

//    @Transactional
//    public ResponseEntity<AuthResponse> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {
//        AuthProviderType authProviderType =authUtil.getAuthProviderTypeFromRegistrationId(registrationId);
//        String providerId= authUtil.determineProviderIdFromOAuth2User(oAuth2User , registrationId );
//
//        User user = (User) userRepository.findByProviderIdAndProviderType(providerId,authProviderType).orElse(null);
//        String email = oAuth2User.getAttribute("email");
//
//        User emailUser= userRepository.findByUsername(email).orElse(null);
//
//        if(user ==null && emailUser == null){
//            String username= authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId );
//            user = signUpInternal(new LoginRequestDto(username,null),authProviderType, providerId);
//        }else if (user!=null){
//            if(email !=null && !email.isBlank() && !email.equals(user.getUsername())){
//                user.setUsername(email);
//                userRepository.save(user);
//            }
//        }else{
//            throw  new BadCredentialsException("This mail already existed with "+emailUser.getProviderType());
//        }
//
//        LogginResponseDto logginResponseDto = new LogginResponseDto(authUtil.generateAccessToken(user),user.getId());
//        return ResponseEntity.ok(logginResponseDto);
//    }
}