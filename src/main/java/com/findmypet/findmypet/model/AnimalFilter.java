package com.findmypet.findmypet.model;

public record AnimalFilter(String nomeAnimal, String especieAnimal, String porte, Integer idadeEstimada, AnimalTipo tipoAnimal) {
}