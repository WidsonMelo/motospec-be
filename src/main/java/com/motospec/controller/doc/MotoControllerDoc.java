package com.motospec.controller.doc;

import com.motospec.dto.MotoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Tag(name = "Motos", description = "Endpoints para gerenciamento de motos")
@SecurityRequirement(name = "bearerAuth")
public interface MotoControllerDoc {

    @Operation(summary = "Listar todas as motos com filtros", 
               description = "Retorna uma lista paginada de motos com opção de filtrar por marca, modelo, ano, preço, cor, cilindrada e descrição. Acessível para USER e ADMIN")
    ResponseEntity<Page<MotoResponse>> getAllMotos(
            @Parameter(description = "Filtrar por marca (busca parcial)") 
            @RequestParam(required = false) String marca,
            
            @Parameter(description = "Filtrar por modelo (busca parcial)") 
            @RequestParam(required = false) String modelo,
            
            @Parameter(description = "Filtrar por ano exato") 
            @RequestParam(required = false) Integer ano,
            
            @Parameter(description = "Preço mínimo") 
            @RequestParam(required = false) BigDecimal precoMin,
            
            @Parameter(description = "Preço máximo") 
            @RequestParam(required = false) BigDecimal precoMax,
            
            @Parameter(description = "Filtrar por cor (busca parcial)") 
            @RequestParam(required = false) String cor,
            
            @Parameter(description = "Cilindrada mínima") 
            @RequestParam(required = false) Integer cilindradaMin,
            
            @Parameter(description = "Cilindrada máxima") 
            @RequestParam(required = false) Integer cilindradaMax,
            
            @Parameter(description = "Filtrar por descrição (busca parcial)") 
            @RequestParam(required = false) String descricao,
            
            @Parameter(description = "Número da página (começa em 0)") 
            @RequestParam(defaultValue = "0") Integer page,
            
            @Parameter(description = "Tamanho da página") 
            @RequestParam(defaultValue = "10") Integer size,
            
            @Parameter(description = "Campo para ordenação (id, marca, modelo, ano, preco, cilindrada)") 
            @RequestParam(defaultValue = "id") String sortBy,
            
            @Parameter(description = "Direção da ordenação (ASC ou DESC)") 
            @RequestParam(defaultValue = "ASC") String sortDirection
    );

    @Operation(summary = "Buscar moto por ID", description = "Retorna o detalhamento de uma moto específica. Acessível para USER e ADMIN")
    ResponseEntity<MotoResponse> getMotoById(@PathVariable Long motoId);
}
