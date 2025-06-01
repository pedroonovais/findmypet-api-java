package com.findmypet.findmypet.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
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
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoa;

    @NotBlank(message = "O campo nome é obrigatório")	
    private String nome;

    @CPF
    private String cpf;

    @NotBlank(message = "O campo telefone é obrigatório")
    private String telefone;

    private PessoaTipo tipoPessoa;

    @Email()
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    private String senha;

}
