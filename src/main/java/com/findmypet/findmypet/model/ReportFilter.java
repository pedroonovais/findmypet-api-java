package com.findmypet.findmypet.model;

import java.time.LocalDate;

public record ReportFilter(
    Long idPessoa,
    Long idAnimal,
    Long idLocal,
    Long idSensor,
    String tipoDesastre,
    LocalDate dataReport
) {
}
