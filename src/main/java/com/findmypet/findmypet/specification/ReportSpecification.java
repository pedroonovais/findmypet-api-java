package com.findmypet.findmypet.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.findmypet.findmypet.model.Report;
import com.findmypet.findmypet.model.ReportFilter;

import jakarta.persistence.criteria.Predicate;

public class ReportSpecification {

    public static Specification<Report> withFilters(ReportFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.idPessoa() != null) {
                predicates.add(cb.equal(root.get("pessoa").get("id"), filter.idPessoa()));
            }

            if (filter.idAnimal() != null) {
                predicates.add(cb.equal(root.get("animal").get("id"), filter.idAnimal()));
            }

            if (filter.idLocal() != null) {
                predicates.add(cb.equal(root.get("local").get("id"), filter.idLocal()));
            }

            if (filter.idSensor() != null) {
                predicates.add(cb.equal(root.get("sensor").get("id"), filter.idSensor()));
            }

            if (filter.tipoDesastre() != null) {
                predicates.add(cb.like(cb.lower(root.get("tipoDesastre")), "%" + filter.tipoDesastre().toLowerCase() + "%"));
            }

            if (filter.dataReport() != null) {
                predicates.add(cb.equal(root.get("dataReport"), filter.dataReport()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
