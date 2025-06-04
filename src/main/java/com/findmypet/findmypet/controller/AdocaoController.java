package com.findmypet.findmypet.controller;

import com.findmypet.findmypet.model.Adocao;
import com.findmypet.findmypet.model.AdocaoFilter;
import com.findmypet.findmypet.repository.AdocaoRepository;
import com.findmypet.findmypet.specification.AdocaoSpecfication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/adocao")
@Slf4j
@Cacheable(value = "adocao")
public class AdocaoController {

    @Autowired
    private AdocaoRepository repository;

    @GetMapping
    public Page<Adocao> index(AdocaoFilter filter, @PageableDefault(size = 10, sort = "id") Pageable page) {
        return repository.findAll(AdocaoSpecfication.withFilters(filter), page);
    }

    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400"))
    @ResponseStatus(HttpStatus.CREATED)
    public Adocao create(@RequestBody Adocao adocao) {
        log.info("Cadastrando uma Adoção: {}", adocao);
        return repository.save(adocao);
    }

    @GetMapping("{id}")
    public Adocao get(@PathVariable Long id) {
        log.info("Buscando Adoção com id: {}", id);
        return getAdocao(id);
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        log.info("Deletando Adoção com id: {}", id);
        repository.delete(getAdocao(id));
    }

    @PutMapping("{id}")
    public Adocao update(@PathVariable Long id, @RequestBody Adocao adocao) {
        log.info("Atualizando Adoção com id: {}", id);

        getAdocao(id);
        adocao.setIdAdocao(id);
        return repository.save(adocao);
    }

    private Adocao getAdocao(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Adoção " + id + " não encontrada"));
    }
}