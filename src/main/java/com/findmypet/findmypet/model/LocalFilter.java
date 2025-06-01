package com.findmypet.findmypet.model;

import java.time.LocalDateTime;

public record LocalFilter(String cidade, String estado, DesastreTipo tipoDesastre, LocalDateTime dataInicioOcorrencia, LocalDateTime dataFimOcorrencia) {
}
