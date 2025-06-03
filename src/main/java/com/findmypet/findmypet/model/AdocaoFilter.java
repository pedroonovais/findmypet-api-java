package com.findmypet.findmypet.model;

import java.time.LocalDate;

public record AdocaoFilter(String status, LocalDate dataInicioAdocao, LocalDate dataFimAdocao) {
}