package com.motospec.specification;

import com.motospec.dto.MotoFilterRequest;
import com.motospec.entity.Moto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MotoSpecification {

    public static Specification<Moto> withFilters(MotoFilterRequest filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getMarca() != null && !filter.getMarca().isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("marca")),
                        "%" + filter.getMarca().toLowerCase() + "%"
                ));
            }

            if (filter.getModelo() != null && !filter.getModelo().isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("modelo")),
                        "%" + filter.getModelo().toLowerCase() + "%"
                ));
            }

            if (filter.getAno() != null) {
                predicates.add(criteriaBuilder.equal(root.get("ano"), filter.getAno()));
            }

            if (filter.getPrecoMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("preco"), filter.getPrecoMin()
                ));
            }

            if (filter.getPrecoMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("preco"), filter.getPrecoMax()
                ));
            }

            if (filter.getCor() != null && !filter.getCor().isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("cor")),
                        "%" + filter.getCor().toLowerCase() + "%"
                ));
            }

            if (filter.getCilindradaMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        root.get("cilindrada"), filter.getCilindradaMin()
                ));
            }

            if (filter.getCilindradaMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        root.get("cilindrada"), filter.getCilindradaMax()
                ));
            }

            if (filter.getDescricao() != null && !filter.getDescricao().isBlank()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("descricao")),
                        "%" + filter.getDescricao().toLowerCase() + "%"
                ));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
