package com.example.demo.repository;

import com.example.demo.tables.Report;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report, Long> {
}
