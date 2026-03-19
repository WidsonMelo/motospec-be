package com.motospec.service;

import com.motospec.dto.RegisterRequest;
import com.motospec.dto.UserResponse;
import com.motospec.entity.Role;
import com.motospec.entity.User;
import com.motospec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse registerUser(RegisterRequest request) {
        // Verificar se o email já existe
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email já existe");
        }

        // Criar novo usuário
        User user = new User();
        user.setEmail(request.getEmail());
        user.setNome(request.getNome());
        user.setSobrenome(request.getSobrenome());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Definir role usando o enum
        Role role = Role.fromString(request.getRole());
        user.setRole(role);

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(), 
                savedUser.getEmail(), 
                savedUser.getNome(), 
                savedUser.getSobrenome(), 
                savedUser.getRole().getValue()
        );
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getNome(),
                        user.getSobrenome(),
                        user.getRole().getValue()
                ))
                .collect(Collectors.toList());
    }
}
