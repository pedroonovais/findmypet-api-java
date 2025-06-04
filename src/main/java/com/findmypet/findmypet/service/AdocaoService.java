package com.findmypet.findmypet.service;

import com.findmypet.findmypet.dto.AdocaoDTO;
import com.findmypet.findmypet.model.*;
import com.findmypet.findmypet.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final PessoaRepository pessoaRepository;
    private final AnimalRepository animalRepository;

    public Adocao createAdocao(AdocaoDTO request) {
        Adocao adocao = buildAdocaoFromRequest(request);
        return adocaoRepository.save(adocao);
    }

    public Adocao updateAdocao(Long id, AdocaoDTO request) {
        getAdocao(id); // garante que existe
        Adocao adocao = buildAdocaoFromRequest(request);
        adocao.setIdAdocao(id);
        return adocaoRepository.save(adocao);
    }

    public Adocao getAdocao(Long id) {
        return adocaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Adoção " + id + " não encontrada"));
    }

    public void deleteAdocao(Long id) {
        adocaoRepository.delete(getAdocao(id));
    }

    private Adocao buildAdocaoFromRequest(AdocaoDTO request) {
        Pessoa pessoa = pessoaRepository.findById(request.getIdPessoa())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));

        Animal animal = animalRepository.findById(request.getIdAnimal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal não encontrado"));

        return Adocao.builder()
                .pessoa(pessoa)
                .animal(animal)
                .dataAdocao(request.getDataAdocao())
                .status(request.getStatus())
                .build();
    }
}
