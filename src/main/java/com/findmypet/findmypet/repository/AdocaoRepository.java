package com.findmypet.findmypet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.findmypet.findmypet.model.Adocao;

public interface AdocaoRepository extends JpaRepository<Adocao, Long>, JpaSpecificationExecutor<Adocao> {
}
