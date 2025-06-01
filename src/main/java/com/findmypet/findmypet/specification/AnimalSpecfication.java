package com.findmypet.findmypet.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.findmypet.findmypet.model.Animal;
import com.findmypet.findmypet.model.AnimalFilter;

import jakarta.persistence.criteria.Predicate;

public class AnimalSpecfication {
    
    public static Specification<Animal> withFilters(AnimalFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.nomeAnimal() != null) {
                predicates.add(cb.like(root.get("nomeAnimal"), "%" + filter.nomeAnimal() + "%"));
            }

            if (filter.especieAnimal() != null) {
                predicates.add(cb.like(root.get("especieAnimal"), "%" + filter.especieAnimal() + "%"));
            }

            if (filter.porte() != null) {
                predicates.add(cb.like(root.get("porte"), "%" + filter.porte() + "%"));
            }

            if (filter.idadeEstimada() != null) {
                predicates.add(cb.equal(root.get("idadeEstimada"), filter.idadeEstimada()));
            }

            if (filter.tipoAnimal() != null) {
                predicates.add(cb.equal(root.get("tipoAnimal"), filter.tipoAnimal()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
