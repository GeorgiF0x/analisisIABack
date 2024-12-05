package com.analisis_ia.demo.repositories;

import com.analisis_ia.demo.models.FacturacionAnual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturacionAnualRepository extends JpaRepository<FacturacionAnual, Integer> {
}
