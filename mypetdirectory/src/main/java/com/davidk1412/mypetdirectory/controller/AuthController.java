package com.davidk1412.mypetdirectory.controller;

import com.davidk1412.mypetdirectory.dto.LoginRequest;
import com.davidk1412.mypetdirectory.dto.RegisterRequest;
import com.davidk1412.mypetdirectory.entity.User;
import com.davidk1412.mypetdirectory.repository.UserRepository;
import com.davidk1412.mypetdirectory.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email ya registrado");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhone());
        userRepo.save(user);

        return ResponseEntity.ok("Usuario registrado");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        String token = jwtTokenProvider.generateToken(
                new org.springframework.security.core.userdetails.User(user.getEmail(), "", List.of())
        );

        return ResponseEntity.ok(Map.of("token", token));
    }
}
