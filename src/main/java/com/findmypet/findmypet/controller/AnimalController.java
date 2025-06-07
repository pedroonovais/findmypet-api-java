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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.findmypet.findmypet.model.Animal;
import com.findmypet.findmypet.model.AnimalFilter;
import com.findmypet.findmypet.repository.AnimalRepository;
import com.findmypet.findmypet.specification.AnimalSpecfication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/animal")
@Slf4j
@Cacheable(value = "animal")
public class AnimalController {
    
    @Autowired
    private AnimalRepository repository;

    @GetMapping
    public Page<Animal> index(AnimalFilter filter, @PageableDefault(size = 10, sort = "idAnimal") Pageable page){
        return repository.findAll(AnimalSpecfication.withFilters(filter), page);
    }
    
    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400"))
    @ResponseStatus(HttpStatus.CREATED)
    public Animal create(@RequestBody Animal animal){
        log.info("Cadastrando um Animal: {}", animal);
        return repository.save(animal);
    }

    @GetMapping("{id}")
    public Animal get(@PathVariable Long id) {
        log.info("Buscando Animal com id: {}", id);
        return getAnimal(id);
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        log.info("Deletando Animal com id: {}", id);
        repository.delete(getAnimal(id));
    }
    
    @PutMapping("{id}")
    public Animal update(@PathVariable Long id, @RequestBody Animal animal) {
        log.info("Atualizando Animal com id: {}", id);

        getAnimal(id);
        animal.setIdAnimal(id);
        return repository.save(animal);
    }

    private Animal getAnimal(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal " + id + " não encontrada"));
    }
}
