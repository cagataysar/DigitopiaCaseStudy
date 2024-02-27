package com.digitopia.digitopiacasestudy.repository;

import com.digitopia.digitopiacasestudy.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, UUID> {

    Optional<Industry> findByIndustryName(String industryName);
}
