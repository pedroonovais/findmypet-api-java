package com.findmypet.findmypet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.findmypet.findmypet.model.Pessoa;
import com.findmypet.findmypet.model.PessoaFilter;
import com.findmypet.findmypet.repository.PessoaRepository;
import com.findmypet.findmypet.specification.PessoaSpecfication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    
    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400"))
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa create(@RequestBody Pessoa pessoa){
        log.info("Cadastrando uma pessoa: {}", pessoa);
        return repository.save(pessoa);
    }

    @GetMapping("{id}")
    public Pessoa get(@RequestParam Long id) {
        log.info("Buscando Pessoa com id: {}", id);
        return getPessoa(id);
    }

    @DeleteMapping("{id}")
    public void destroy(@RequestParam Long id) {
        log.info("Deletando Pessoa com id: {}", id);
        repository.delete(getPessoa(id));
    }
    
    @PutMapping("path/{id}")
    public Pessoa update(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        log.info("Atualizando Pessoa com id: {}", id);

        getPessoa(id);
        pessoa.setIdPessoa(id);
        return repository.save(pessoa);
    }

    private Pessoa getPessoa(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor " + id + " não encontrada"));
    }
}
