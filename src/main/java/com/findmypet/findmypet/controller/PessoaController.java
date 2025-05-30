package com.findmypet.findmypet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findmypet.findmypet.model.Pessoa;
import com.findmypet.findmypet.model.PessoaFilter;
import com.findmypet.findmypet.repository.PessoaRepository;
import com.findmypet.findmypet.specification.PessoaSpecfication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/pessoa")
@Slf4j
@Cacheable(value = "pessoa")
public class PessoaController {
    
    @Autowired
    private PessoaRepository repository;

    @GetMapping
    public Page<Pessoa> index(PessoaFilter filter, @PageableDefault(size = 10, sort = "idPessoa") Pageable page){
        return repository.findAll(PessoaSpecfication.withFilters(filter), page);
    }
    

    
}
