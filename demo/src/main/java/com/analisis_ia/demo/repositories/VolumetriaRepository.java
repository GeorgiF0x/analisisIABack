package com.analisis_ia.demo.repositories;

import com.analisis_ia.demo.models.Volumetria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolumetriaRepository extends JpaRepository<Volumetria, Integer> {
}
