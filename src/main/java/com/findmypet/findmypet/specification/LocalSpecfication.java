package com.findmypet.findmypet.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.findmypet.findmypet.model.Local;
import com.findmypet.findmypet.model.LocalFilter;

import jakarta.persistence.criteria.Predicate;

public class LocalSpecfication {
    
    public static Specification<Local> withFilters(LocalFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.cidade() != null) {
                predicates.add(cb.like(root.get("cidade"), "%" + filter.cidade() + "%"));
            }
            
            if (filter.estado() != null) {
                predicates.add(cb.like(root.get("estado"), "%" + filter.estado() + "%"));
            }

            if (filter.tipoDesastre() != null) {
                predicates.add(cb.equal(root.get("tipoDesastre"), filter.tipoDesastre()));
            }

            if (filter.dataInicioOcorrencia() != null && filter.dataFimOcorrencia() != null) {
                predicates.add(
                        cb.between(root.get("dataInicioOcorrencia"), filter.dataInicioOcorrencia(), filter.dataFimOcorrencia()));
            }

            if (filter.dataInicioOcorrencia() != null && filter.dataFimOcorrencia() == null) {
                predicates.add(
                        cb.equal(root.get("dataInicioOcorrencia"), filter.dataInicioOcorrencia()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
