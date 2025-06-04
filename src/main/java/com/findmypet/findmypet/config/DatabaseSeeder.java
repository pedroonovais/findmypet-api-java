package com.findmypet.findmypet.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.findmypet.findmypet.model.Adocao;
import com.findmypet.findmypet.model.Animal;
import com.findmypet.findmypet.model.AnimalTipo;
import com.findmypet.findmypet.model.DesastreTipo;
import com.findmypet.findmypet.model.Local;
import com.findmypet.findmypet.model.Pessoa;
import com.findmypet.findmypet.model.PessoaTipo;
import com.findmypet.findmypet.model.Report;
import com.findmypet.findmypet.model.Sensor;
import com.findmypet.findmypet.model.SensorTipo;
import com.findmypet.findmypet.model.UserRole;
import com.findmypet.findmypet.repository.AdocaoRepository;
import com.findmypet.findmypet.repository.AnimalRepository;
import com.findmypet.findmypet.repository.LocalRepository;
import com.findmypet.findmypet.repository.PessoaRepository;
import com.findmypet.findmypet.repository.ReportRepository;
import com.findmypet.findmypet.repository.SensorRepository;

import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private PessoaRepository userRepository;

    @Autowired
    private AdocaoRepository adocaoRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private SensorRepository sensorRepository;

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

        var animais = List.of(
            Animal.builder().nomeAnimal("Rex").especieAnimal("Cachorro").porte("Pequeno").idadeEstimada(2).tipoAnimal(AnimalTipo.Resgatado).build(),
            Animal.builder().nomeAnimal("Toby").especieAnimal("Cachorro").porte("Pequeno").idadeEstimada(1).tipoAnimal(AnimalTipo.Resgatado).build(),
            Animal.builder().nomeAnimal("Polly").especieAnimal("Cachorro").porte("Pequeno").idadeEstimada(1).tipoAnimal(AnimalTipo.Resgatado).build(),
            Animal.builder().nomeAnimal("Luna").especieAnimal("Cachorro").porte("Pequeno").idadeEstimada(1).tipoAnimal(AnimalTipo.Resgatado).build(),
            Animal.builder().nomeAnimal("Nala").especieAnimal("Cachorro").porte("Pequeno").idadeEstimada(1).tipoAnimal(AnimalTipo.Resgatado).build()
        );

        animalRepository.saveAll(animais);

        var locais = List.of(
            Local.builder().cidade("Sao Paulo").estado("SP").tipoDesastre(DesastreTipo.Deslizamento).dataOcorrencia(LocalDateTime.now()).build(),
            Local.builder().cidade("Rio de Janeiro").estado("RJ").tipoDesastre(DesastreTipo.Incêndio).dataOcorrencia(LocalDateTime.now()).build(),
            Local.builder().cidade("Belo Horizonte").estado("MG").tipoDesastre(DesastreTipo.Incêndio).dataOcorrencia(LocalDateTime.now()).build()
        );

        localRepository.saveAll(locais);

        var sensores = List.of(
            Sensor.builder().tipoSensor(SensorTipo.Movimento).latitude(0).longitude(0).ativo(true).build(),
            Sensor.builder().tipoSensor(SensorTipo.Temperatura).latitude(0).longitude(0).ativo(true).build(),
            Sensor.builder().tipoSensor(SensorTipo.Calor).latitude(0).longitude(0).ativo(true).build()
        );

        sensorRepository.saveAll(sensores);

        var adocoes = List.of(
            Adocao.builder().pessoa(joao).animal(animais.get(0)).dataAdocao(LocalDate.now()).status("Adotado").build(),
            Adocao.builder().pessoa(davi).animal(animais.get(1)).dataAdocao(LocalDate.now()).status("Adotado").build()
        );

        adocaoRepository.saveAll(adocoes);

        var reports = List.of(
            Report.builder().pessoa(joao).animal(animais.get(2)).local(locais.get(0)).sensor(sensores.get(0)).descricao("Desc").tipoDesastre("Incendio").latitude(-23.5).longitude(-46.5).dataReport(LocalDate.now()).build(),
            Report.builder().pessoa(davi).animal(animais.get(3)).local(locais.get(1)).sensor(sensores.get(1)).descricao("Desc").tipoDesastre("Incendio").latitude(-23.5).longitude(-46.5).dataReport(LocalDate.now()).build()
        );

        reportRepository.saveAll(reports);
    }

}

