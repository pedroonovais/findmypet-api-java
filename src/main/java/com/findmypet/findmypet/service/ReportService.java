package com.findmypet.findmypet.service;

import com.findmypet.findmypet.dto.ReportDTO;
import com.findmypet.findmypet.model.*;
import com.findmypet.findmypet.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final PessoaRepository pessoaRepository;
    private final AnimalRepository animalRepository;
    private final LocalRepository localRepository;
    private final SensorRepository sensorRepository;

    public Report createReport(ReportDTO request) {
        Report report = buildReportFromRequest(request);
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, ReportDTO request) {
        reportRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report " + id + " não encontrado"));

        Report updated = buildReportFromRequest(request);
        updated.setIdReport(id);
        return reportRepository.save(updated);
    }

    private Report buildReportFromRequest(ReportDTO request) {
        Pessoa pessoa = pessoaRepository.findById(request.getIdPessoa())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        Animal animal = animalRepository.findById(request.getIdAnimal())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrado"));

        Local local = localRepository.findById(request.getIdLocal())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Local não encontrado"));

        Sensor sensor = sensorRepository.findById(request.getIdSensor())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor não encontrado"));

        return Report.builder()
            .pessoa(pessoa)
            .animal(animal)
            .local(local)
            .sensor(sensor)
            .descricao(request.getDescricao())
            .tipoDesastre(request.getTipoDesastre())
            .latitude(request.getLatitude())
            .longitude(request.getLongitude())
            .dataReport(request.getDataReport())
            .build();
    }
}
