package com.motospec.service;

import com.motospec.dto.MotoFilterRequest;
import com.motospec.dto.MotoResponse;
import com.motospec.entity.Moto;
import com.motospec.repository.MotoRepository;
import com.motospec.specification.MotoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MotoService {

    private final MotoRepository motoRepository;

    @Transactional(readOnly = true)
    public Page<MotoResponse> getAllMotos(MotoFilterRequest filter) {
        // Criar Specification com os filtros
        Specification<Moto> spec = MotoSpecification.withFilters(filter);
        
        // Criar Pageable com os parâmetros de paginação e ordenação
        Sort.Direction direction = filter.getSortDirection().equalsIgnoreCase("DESC") 
                ? Sort.Direction.DESC 
                : Sort.Direction.ASC;
        
        Pageable pageable = PageRequest.of(
                filter.getPage(),
                filter.getSize(),
                Sort.by(direction, filter.getSortBy())
        );
        
        return motoRepository.findAll(spec, pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public MotoResponse getMotoById(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moto não encontrada"));
        return toResponse(moto);
    }

    private MotoResponse toResponse(Moto moto) {
        return new MotoResponse(
                moto.getId(),
                moto.getMarca(),
                moto.getModelo(),
                moto.getAno(),
                moto.getPreco(),
                moto.getCor(),
                moto.getCilindrada(),
                moto.getDescricao(),
                moto.getCreatedAt(),
                moto.getUpdatedAt()
        );
    }
}
