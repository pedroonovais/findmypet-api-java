package com.findmypet.findmypet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;

    @NotBlank(message = "O campo nome é obrigatório")
    private String nomeAnimal;

    @NotBlank(message = "O campo espécie é obrigatório")
    private String especieAnimal;

    @NotBlank(message = "O campo porte é obrigatório")
    private String porte;

    @Min(value = 0, message = "A idade estimada não pode ser negativa")
    private int idadeEstimada;

    private AnimalTipo tipoAnimal;
}
