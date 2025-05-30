package com.findmypet.findmypet.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.findmypet.findmypet.model.Pessoa;
import com.findmypet.findmypet.model.PessoaFilter;

import jakarta.persistence.criteria.Predicate;

public class PessoaSpecfication {
    
    public static Specification<Pessoa> withFilters(PessoaFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.nome() != null) {
                predicates.add(cb.like(root.get("nome"), "%" + filter.nome() + "%"));
            }

            if (filter.cpf() != null) {
                predicates.add(cb.like(root.get("cpf"), "%" + filter.cpf() + "%"));
            }

            if (filter.telefone() != null) {
                predicates.add(cb.like(root.get("telefone"), "%" + filter.telefone() + "%"));
            }

            if (filter.email() != null) {
                predicates.add(cb.like(root.get("email"), "%" + filter.email() + "%"));
            }

            if (filter.tipoPessoa() != null) {
                predicates.add(cb.equal(root.get("tipoPessoa"), filter.tipoPessoa()));
            }

            var arrayPredicates = predicates.toArray(new Predicate[0]);
            return cb.and(arrayPredicates);
        };
    }
}
