package com.findmypet.findmypet.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportDTO {
    private Long idPessoa;
    private Long idAnimal;
    private Long idLocal;
    private Long idSensor;
    private String descricao;
    private String tipoDesastre;
    private Double latitude;
    private Double longitude;
    private LocalDate dataReport;
}
