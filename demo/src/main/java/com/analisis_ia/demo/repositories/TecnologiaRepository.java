package com.analisis_ia.demo.repositories;

import com.analisis_ia.demo.models.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
}