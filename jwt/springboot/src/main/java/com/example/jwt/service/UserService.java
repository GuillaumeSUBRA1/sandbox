package com.example.jwt.service;

import com.example.jwt.dto.UserDTO;
import com.example.jwt.dto.UserRecord;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.mapper.UserMapper;
import com.example.jwt.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    // ✅ Création d’un nouvel utilisateur
    @Transactional
    public UserEntity createUser(UserRecord user) {

        if (userRepository.existsByEmail(user.email())) {
            throw new RuntimeException("Nom d'utilisateur déjà utilisé");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.username());
        userEntity.setEmail(user.email());
        userEntity.setPassword(passwordEncoder.encode(user.password()));

        return userRepository.save(userEntity);
    }

    // 🔍 Chargement d’un utilisateur pour Spring Security
    public UserDTO findByEmailAndPassword(String email, String password) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmailAndPassword(email, passwordEncoder.encode(password));
        if(userEntity.isEmpty()) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }
        return userMapper.entityToDTO(userEntity.get());

    }
}
