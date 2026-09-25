package com.e3s.mercedes.repository;

import com.e3s.mercedes.entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
}