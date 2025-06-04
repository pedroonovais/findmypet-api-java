package com.findmypet.findmypet.controller;

import com.findmypet.findmypet.model.Report;
import com.findmypet.findmypet.model.ReportFilter;
import com.findmypet.findmypet.repository.ReportRepository;
import com.findmypet.findmypet.service.ReportService;
import com.findmypet.findmypet.specification.ReportSpecification;
import com.findmypet.findmypet.dto.ReportRequest;

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
@RequestMapping("/report")
@Slf4j
@Cacheable(value = "report")
public class ReportController {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private ReportService reportService;

    @GetMapping
    public Page<Report> index(ReportFilter filter, @PageableDefault(size = 10, sort = "id") Pageable page) {
        return repository.findAll(ReportSpecification.withFilters(filter), page);
    }

    @PostMapping
    @Operation(responses = @ApiResponse(responseCode = "400"))
    @ResponseStatus(HttpStatus.CREATED)
    public Report create(@RequestBody ReportRequest request) {
        log.info("Cadastrando Report: {}", request);
        return reportService.createReport(request);
    }

    @GetMapping("{id}")
    public Report get(@PathVariable Long id) {
        log.info("Buscando Report com id: {}", id);
        return getReport(id);
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        log.info("Deletando Report com id: {}", id);
        repository.delete(getReport(id));
    }

    @PutMapping("{id}")
    public Report update(@PathVariable Long id, @RequestBody ReportRequest request) {
        log.info("Atualizando Report com id: {}", id);
        getReport(id);
        return reportService.updateReport(id, request);
    }

    private Report getReport(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report " + id + " não encontrado"));
    }
}
