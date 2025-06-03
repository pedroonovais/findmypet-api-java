package com.findmypet.findmypet.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.findmypet.findmypet.model.Adocao;
import com.findmypet.findmypet.model.AdocaoFilter;

import jakarta.persistence.criteria.Predicate;

public class AdocaoSpecfication {
    
    public static Specification<Adocao> withFilters(AdocaoFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.status() != null) {
                predicates.add(cb.equal(root.get("status"), filter.status()));
            }

            if (filter.dataInicioAdocao() != null && filter.dataFimAdocao() != null) {
                predicates.add(cb.between(root.get("dataAdocao"), filter.dataInicioAdocao(), filter.dataFimAdocao()));
            }

            if (filter.dataInicioAdocao() != null && filter.dataFimAdocao() == null) {
                predicates.add(cb.equal(root.get("dataAdocao"), filter.dataInicioAdocao()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
