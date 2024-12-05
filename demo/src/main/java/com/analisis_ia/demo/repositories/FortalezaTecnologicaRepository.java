package com.analisis_ia.demo.repositories;

import com.analisis_ia.demo.models.FortalezaTecnologica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FortalezaTecnologicaRepository extends JpaRepository<FortalezaTecnologica, Integer> {
}
