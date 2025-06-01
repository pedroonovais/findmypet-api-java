package com.findmypet.findmypet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.findmypet.findmypet.model.Local;

public interface LocalRepository extends JpaRepository<Local, Long>, JpaSpecificationExecutor<Local> {
}
