package com.e3s.mercedes.repository;

import com.e3s.mercedes.entity.Actualite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActualiteRepository extends JpaRepository<Actualite, Long> {
}