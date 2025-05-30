package com.findmypet.findmypet.dto;

public record PessoaDTO(
    Long idPessoa,
    String nome,
    String cpf,
    String telefone,
    String email
) {}
