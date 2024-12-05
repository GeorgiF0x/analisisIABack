package com.analisis_ia.demo.repositories;

import com.analisis_ia.demo.models.PrecioHora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecioHoraRepository extends JpaRepository<PrecioHora, Integer> {
}
