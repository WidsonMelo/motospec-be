package com.motospec.controller;

import com.motospec.controller.doc.MotoControllerDoc;
import com.motospec.dto.MotoFilterRequest;
import com.motospec.dto.MotoResponse;
import com.motospec.service.MotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/motos")
@RequiredArgsConstructor
public class MotoController implements MotoControllerDoc {

    private final MotoService motoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<MotoResponse>> getAllMotos(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax,
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer cilindradaMin,
            @RequestParam(required = false) Integer cilindradaMax,
            @RequestParam(required = false) String descricao,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        
        MotoFilterRequest filter = new MotoFilterRequest();
        filter.setMarca(marca);
        filter.setModelo(modelo);
        filter.setAno(ano);
        filter.setPrecoMin(precoMin);
        filter.setPrecoMax(precoMax);
        filter.setCor(cor);
        filter.setCilindradaMin(cilindradaMin);
        filter.setCilindradaMax(cilindradaMax);
        filter.setDescricao(descricao);
        filter.setPage(page);
        filter.setSize(size);
        filter.setSortBy(sortBy);
        filter.setSortDirection(sortDirection);
        
        Page<MotoResponse> motos = motoService.getAllMotos(filter);
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{motoId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MotoResponse> getMotoById(@PathVariable Long motoId) {
        MotoResponse moto = motoService.getMotoById(motoId);
        return ResponseEntity.ok(moto);
    }
}
