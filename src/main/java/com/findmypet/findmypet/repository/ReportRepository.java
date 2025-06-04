package com.findmypet.findmypet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.findmypet.findmypet.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report> {   
}