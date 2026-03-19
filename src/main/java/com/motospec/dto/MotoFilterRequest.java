package com.motospec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoFilterRequest {
    
    private String marca;
    private String modelo;
    private Integer ano;
    private BigDecimal precoMin;
    private BigDecimal precoMax;
    private String cor;
    private Integer cilindradaMin;
    private Integer cilindradaMax;
    private String descricao;
    
    // Campos de paginação
    private Integer page = 0;
    private Integer size = 10;
    
    // Campos de ordenação
    private String sortBy = "id";
    private String sortDirection = "ASC";
}
