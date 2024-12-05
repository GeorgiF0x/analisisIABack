package com.analisis_ia.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int duracion;

    private Date fechaFin;
    private Date fechaInicio;
    private Date fechaRegistro;

    private String nombreProyecto;
    private Double presupuesto;

    @Column(columnDefinition = "TINYINT")
    private Byte resultado;

    private String cliente;

    @ManyToOne(fetch = FetchType.LAZY) // Relación de muchos a uno
    @JoinColumn(name = "certificaciones_requeridas_id", referencedColumnName = "id")
    private CertificacionRequerida certificacionRequerida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entregables_id", referencedColumnName = "id")
    private Entregable entregable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "experiencia_requerida_id", referencedColumnName = "id")
    private ExperienciaRequerida experienciaRequerida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facturacion_anual_id", referencedColumnName = "id")
    private FacturacionAnual facturacionAnual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fortaleza_tecnologica_id", referencedColumnName = "id")
    private FortalezaTecnologica fortalezaTecnologica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idiomas_id", referencedColumnName = "id")
    private Idioma idioma;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lugar_trabajo_id", referencedColumnName = "id")
    private LugarTrabajo lugarTrabajo;

    private int numeroPerfilesRequeridos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precio_hora_id", referencedColumnName = "id")
    private PrecioHora precioHora;

    private String solvenciaEconomicaEmpresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titulaciones_empleados_id", referencedColumnName = "id")
    private TitulacionEmpleado titulacionEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volumetria_id", referencedColumnName = "id")
    private Volumetria volumetria;

    private Double porcentajeExito;

    @ManyToMany
    @JoinTable(
        name = "proyectos_tecnologias",
        joinColumns = @JoinColumn(name = "proyecto_id"),
        inverseJoinColumns = @JoinColumn(name = "tecnologia_id")
    )
    private Set<Tecnologia> tecnologias = new HashSet<>();
    
}
