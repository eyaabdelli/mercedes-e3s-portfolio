package com.e3s.mercedes.repository;

import com.e3s.mercedes.entity.DemandeProforma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeProformaRepository
        extends JpaRepository<DemandeProforma, Long> {
}