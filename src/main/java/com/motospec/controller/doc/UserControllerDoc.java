package com.motospec.controller.doc;

import com.motospec.dto.RegisterRequest;
import com.motospec.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Users", description = "Endpoints para gerenciamento de usuários")
public interface UserControllerDoc {

    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário no sistema")
    ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest registerRequest);

    @Operation(summary = "Listar todos os usuários", description = "Retorna todos os usuários cadastrados. Acesso exclusivo para ADMIN")
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<List<UserResponse>> getAllUsers();
}
