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
    @Autowired
    UserMapper userMapper;

    // ✅ Création d’un nouvel utilisateur
    @Transactional
    public UserEntity createUser(UserRecord user) {

        if (userRepository.existsByEmail(user.email())) {
            throw new UserException("Nom d'utilisateur déjà utilisé");
        }
        if(user.password().length() < 8) {
            throw new PasswordException("Le mot de passe doit contenir au moins 8 caractères");
        }
        if (user.email().length() < 3) {
            throw new PasswordException("Le login doit contenir au moins 3 caractères");
        }

        UserEntity userEntity = userMapper.recordToEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.password()));

        return userRepository.save(userEntity);
    }

    // 🔍 Chargement d’un utilisateur pour Spring Security
    public UserDTO findByEmailAndPassword(String email, String password) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByEmailAndPassword(email, passwordEncoder.encode(password));
        if(userEntity.isEmpty()) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if(userEntity.isEmpty()) {
            throw new UserNotFoundException("Aucun utilisateur trouvé");
        }
        if(!passwordEncoder.matches(password, userEntity.get().getPassword())) {
            throw new PasswordException("Le mot de passe est incorrect");
        }
        return userMapper.entityToDTO(userEntity.get());

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
