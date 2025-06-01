package com.findmypet.findmypet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.findmypet.findmypet.model.Sensor;
import com.findmypet.findmypet.model.SensorFilter;
import com.findmypet.findmypet.repository.SensorRepository;
import com.findmypet.findmypet.specification.SensorSpecfication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pessoa")
@Slf4j
@Cacheable(value = "pessoa")
public class SensorController {
    
    @Autowired
    private SensorRepository repository;

    @GetMapping()
    public Page<Sensor> index(SensorFilter filter, @PageableDefault(size = 10, sort = "idSensor") Pageable page){
        return repository.findAll(SensorSpecfication.withFilters(filter), page);
    }
    
    @PostMapping()
    public Sensor create(@RequestBody Sensor sensor){
        log.info("Cadastrando um sensor: {}", sensor);
        return repository.save(sensor);
    }

    @GetMapping("{id}")
    public Sensor get(@RequestParam Long id) {
        log.info("Buscando Sensor com id: {}", id);
        return getSensor(id);
    }

    private Sensor getSensor(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor " + id + " não encontrada"));
    }
}
