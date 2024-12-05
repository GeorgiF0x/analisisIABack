package com.analisis_ia.demo.repositories;

import com.analisis_ia.demo.models.LugarTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LugarTrabajoRepository extends JpaRepository<LugarTrabajo, Integer> {
}
