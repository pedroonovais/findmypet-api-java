package com.findmypet.findmypet.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.findmypet.findmypet.model.Pessoa;
import com.findmypet.findmypet.model.PessoaTipo;
import com.findmypet.findmypet.model.UserRole;
import com.findmypet.findmypet.repository.PessoaRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private PessoaRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        var joao = Pessoa.builder()
                        .nome("João")
                        .cpf("443.600.840-13")
                        .telefone("11999999999")
                        .tipoPessoa(PessoaTipo.Resgatante)
                        .email("joao@fiap.com.br")
                        .senha(passwordEncoder.encode("12345"))
                        .role(UserRole.ADMIN)
                        .build();

        var davi = Pessoa.builder()
                        .nome("Davi")
                        .cpf("858.049.070-77")
                        .telefone("11988888888")
                        .tipoPessoa(PessoaTipo.Adotante)
                        .email("davi@fiap.com.br")
                        .senha(passwordEncoder.encode("1234"))
                        .role(UserRole.USER)
                        .build();

        userRepository.saveAll(List.of(joao, davi));
    }

}

