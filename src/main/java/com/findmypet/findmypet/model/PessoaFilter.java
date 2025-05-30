package com.findmypet.findmypet.model;

public record PessoaFilter(String nome, String cpf, String telefone, String email, PessoaTipo tipoPessoa) {
}