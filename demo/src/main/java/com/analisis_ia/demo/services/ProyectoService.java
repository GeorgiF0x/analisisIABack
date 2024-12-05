package com.analisis_ia.demo.services;

import com.analisis_ia.demo.models.*;
import com.analisis_ia.demo.repositories.*;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;


    @Autowired
    private FacturacionAnualRepository facturacionAnualRepository;

    @Autowired
    private FortalezaTecnologicaRepository fortalezaTecnologicaRepository;

    @Autowired
    private ExperienciaRequeridaRepository experienciaRequeridaRepository;

    @Autowired
    private LugarTrabajoRepository lugarTrabajoRepository;

    @Autowired
    private PrecioHoraRepository precioHoraRepository;

    @Autowired
    private VolumetriaRepository volumetriaRepository;

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    public FacturacionAnual getFacturacionAnualById(int id) {
        return facturacionAnualRepository.findById(id).orElseThrow(() -> new RuntimeException("Facturación Anual no encontrada"));
    }

    public FortalezaTecnologica getFortalezaTecnologicaById(int id) {
        return fortalezaTecnologicaRepository.findById(id).orElseThrow(() -> new RuntimeException("Fortaleza Tecnológica no encontrada"));
    }

    public ExperienciaRequerida getExperienciaRequeridaById(int id) {
        return experienciaRequeridaRepository.findById(id).orElseThrow(() -> new RuntimeException("Experiencia Requerida no encontrada"));
    }

    public LugarTrabajo getLugarTrabajoById(int id) {
        return lugarTrabajoRepository.findById(id).orElseThrow(() -> new RuntimeException("Lugar de Trabajo no encontrado"));
    }

    public PrecioHora getPrecioHoraById(int id) {
        return precioHoraRepository.findById(id).orElseThrow(() -> new RuntimeException("Precio Hora no encontrado"));
    }

    public Volumetria getVolumetriaById(int id) {
        return volumetriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Volumetría no encontrada"));
    }

    public Tecnologia getTecnologiaById(int id) {
        return tecnologiaRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Tecnología no encontrada"));
    }
    

    @Transactional
    public Proyecto guardarProyecto(Proyecto proyecto) {
        // Asegúrate de que las tecnologías existan antes de guardar
        proyecto.getTecnologias().forEach(tecnologia -> {
            if (!tecnologiaRepository.existsById(tecnologia.getId())) {
                throw new RuntimeException("Tecnología no encontrada: " + tecnologia.getId());
            }
        });
        return proyectoRepository.save(proyecto);
    }
}
