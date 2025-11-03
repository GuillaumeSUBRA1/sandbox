package com.example.jwt.controller;

import com.example.jwt.dto.ConnectedUserDTO;
import com.example.jwt.dto.UserRecord;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.mapper.UserMapper;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.service.JwtService;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    JwtService jwtService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    // ✅ Création de compte
    @PostMapping("/register")
    public ResponseEntity<ConnectedUserDTO> register(@RequestBody UserRecord user) {
        UserEntity userCreated = userService.createUser(user);
        String token = jwtService.generateToken(userCreated.getEmail(),user.password());
        ConnectedUserDTO connectedUserDTO = userMapper.entityToConnectedDTO(userCreated);
        connectedUserDTO.setToken(token);
        return ResponseEntity.ok(connectedUserDTO);
    }

    // 🔐 Connexion
    @PostMapping("/login")
    public ResponseEntity<ConnectedUserDTO> login(@RequestBody ConnectUserRecord user) {
        UserEntity userEntity = userService.findByEmailAndPassword(user.email(), user.password());

        String token = jwtService.generateToken(userEntity.getEmail(), user.password());
        ConnectedUserDTO connectedUserDTO = userMapper.entityToConnectedDTO(userEntity);
        connectedUserDTO.setToken(token);

        return ResponseEntity.ok(connectedUserDTO);
    }

        return ResponseEntity.ok(token);
    }
}
