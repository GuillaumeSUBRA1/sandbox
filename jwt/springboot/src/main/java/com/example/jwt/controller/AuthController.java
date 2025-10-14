package com.example.jwt.controller;

import com.example.jwt.dto.UserDTO;
import com.example.jwt.dto.UserRecord;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.service.JwtService;
import com.example.jwt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    JwtService jwtService;
    UserService userService;
    AuthenticationManager authenticationManager;

    // ✅ Création de compte
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRecord user) {
        UserEntity userCreated = userService.createUser(user);
        String token = jwtService.generateToken(userCreated);
        return ResponseEntity.ok(token);
    }

    // 🔐 Connexion
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRecord user) {
        UserDTO userDTO = userService.findByEmailAndPassword(user.email(), user.password());
        if(userDTO == null) {
            return ResponseEntity.status(401).body("Nom d'utilisateur ou mot de passe incorrect");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.username(), user.password())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }
}
