package com.analisis_ia.demo.models;

import lombok.Data;

import java.util.Set;

@Data
public class ProyectoDTO {
    private int duracion;
    private Double presupuesto;
    private String nombreProyecto;
    private String cliente;
    private int facturacionAnual; 
    private int fortalezaTecnologica; 
    private int experienciaRequerida; 
    private int lugarTrabajo; 
    private int numeroPerfilesRequeridos;
    private int precioHora; 
    private int volumetria; 
    private Set<Integer> tecnologias; 
}
