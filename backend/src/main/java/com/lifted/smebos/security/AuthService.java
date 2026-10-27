package com.lifted.smebos.security;

import com.lifted.smebos.dto.auth.LoginRequest;
import com.lifted.smebos.dto.auth.LoginResponse;
import com.lifted.smebos.entity.User;
import com.lifted.smebos.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            CustomUserDetailsService userDetailsService,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

        User user = userRepository.findByUsername(
                        request.getUsername()
                )
                .orElseThrow();

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        request.getUsername()
                );

        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getRole().name()
        );
    }
}
