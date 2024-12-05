package com.analisis_ia.demo.repositories;

import com.analisis_ia.demo.models.ExperienciaRequerida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRequeridaRepository extends JpaRepository<ExperienciaRequerida, Integer> {
}
