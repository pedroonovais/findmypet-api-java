package com.findmypet.findmypet.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AdocaoDTO {
    private Long idPessoa;
    private Long idAnimal;
    private LocalDate dataAdocao;
    private String status;
}
