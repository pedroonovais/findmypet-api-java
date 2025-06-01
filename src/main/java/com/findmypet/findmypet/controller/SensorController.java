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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/sensor")
@Slf4j
@Cacheable(value = "sensor")
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

    @DeleteMapping("{id}")
    public void destroy(@RequestParam Long id) {
        log.info("Deletando Sensor com id: {}", id);
        repository.delete(getSensor(id));
    }

    @PutMapping("{id}")
    public Sensor update(@PathVariable Long id, @RequestBody Sensor sensor) {
        log.info("Atualizando Sensor com id: {}", id);
        getSensor(id);
        sensor.setIdSensor(id);
        return repository.save(sensor);
    }

    private Sensor getSensor(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor " + id + " não encontrada"));
    }
}
