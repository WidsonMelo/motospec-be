package com.motospec.controller.doc;

import com.motospec.dto.AuthResponse;
import com.motospec.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication", description = "Endpoints para autenticação")
public interface AuthControllerDoc {

    @Operation(summary = "Realizar login", description = "Autentica um usuário e retorna um access token")
    ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest);
}
