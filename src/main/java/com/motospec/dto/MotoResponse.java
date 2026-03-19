package com.motospec.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotoResponse {
    private Long id;
    private String marca;
    private String modelo;
    private Integer ano;
    private BigDecimal preco;
    private String cor;
    private Integer cilindrada;
    private String descricao;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
