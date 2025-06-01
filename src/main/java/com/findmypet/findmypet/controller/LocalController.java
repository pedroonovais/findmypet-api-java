package com.findmypet.findmypet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.findmypet.findmypet.model.Local;
import com.findmypet.findmypet.model.LocalFilter;
import com.findmypet.findmypet.repository.LocalRepository;
import com.findmypet.findmypet.specification.LocalSpecfication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/local")
@Slf4j
@Cacheable(value = "local")
public class LocalController {
    
    @Autowired
    private LocalRepository repository;

    @GetMapping
    public Page<Local> index(LocalFilter filter, @PageableDefault(size = 10, sort = "idLocal") Pageable page){
        return repository.findAll(LocalSpecfication.withFilters(filter), page);
    }
    
    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400"))
    @ResponseStatus(HttpStatus.CREATED)
    public Local create(@RequestBody Local local){
        log.info("Cadastrando um local: {}", local);
        return repository.save(local);
    }

    @GetMapping("{id}")
    public Local get(@RequestParam Long id) {
        log.info("Buscando Local com id: {}", id);
        return getLocal(id);
    }

    @DeleteMapping("{id}")
    public void destroy(@RequestParam Long id) {
        log.info("Deletando Local com id: {}", id);
        repository.delete(getLocal(id));
    }
    
    @PutMapping("{id}")
    public Local update(@PathVariable Long id, @RequestBody Local local) {
        log.info("Atualizando local com id: {}", id);

        getLocal(id);
        local.setIdLocal(id);
        return repository.save(local);
    }

    private Local getLocal(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Local " + id + " não encontrada"));
    }
}
