package com.itacademy.finalprod.project_.controller;


import com.itacademy.finalprod.project_.config.JwtService;
import com.itacademy.finalprod.project_.entity.Role;
import com.itacademy.finalprod.project_.entity.User;
import com.itacademy.finalprod.project_.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest registerRequest) {
    User user = User.builder()
            .firstName(registerRequest.getFirstName())
            .lastName(registerRequest.getLastName())
            .email(registerRequest.getEmail())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .role(Role.USER)
            .build();
      userRepository.save(user);
      var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
              .token(jwtToken)
              .build();
    }


    public AuthenticationResponse authenticate(AuthencticationRequest authencticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authencticationRequest.getEmail(),
                authencticationRequest.getPassword())
        );
        var user = userRepository.findByEmail(authencticationRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
