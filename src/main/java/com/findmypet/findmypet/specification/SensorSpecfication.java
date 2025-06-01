package com.findmypet.findmypet.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.findmypet.findmypet.model.Sensor;
import com.findmypet.findmypet.model.SensorFilter;

import jakarta.persistence.criteria.Predicate;

public class SensorSpecfication {
    
    public static Specification<Sensor> withFilters(SensorFilter filter) {
        return (root, query, cb) -> {
            
            List<Predicate> predicates = new ArrayList<>();
            
            if (filter.latitude() != null) {
                predicates.add(cb.equal(root.get("latitude"), filter.latitude()));
            }

            if (filter.longitude() != null) {
                predicates.add(cb.equal(root.get("longitude"), filter.longitude()));
            }

            if (filter.ativo() != null) {
                predicates.add(cb.equal(root.get("ativo"), filter.ativo()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
