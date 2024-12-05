package com.analisis_ia.demo.models;

import lombok.Data;
import java.util.List;

@Data
public class MicroservicioRequest {
    private int duracion;
    private double presupuesto;
    private String facturacionAnual; // Cambiado de int a String
    private String fortalezaTecnologica;
    private String experienciaRequerida;
    private String lugarTrabajo;
    private int numeroPerfilesRequeridos;
    private String precioHora;
    private String volumetria; // Cambiado de int a String
    private List<String> tecnologias; // Cambiado de List<Integer> a List<String>


}

