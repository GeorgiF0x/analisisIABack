package com.analisis_ia.demo.controllers;

import com.analisis_ia.demo.models.*;
import com.analisis_ia.demo.services.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String IA_SERVICE_URL = "http://127.0.0.1:8000/predict";

    @PostMapping
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody ProyectoDTO proyectoDTO) {
        try {
            // 1. Mapear el DTO al modelo Proyecto
            Proyecto proyecto = new Proyecto();
            proyecto.setDuracion(proyectoDTO.getDuracion());
            proyecto.setPresupuesto(proyectoDTO.getPresupuesto());
            proyecto.setNombreProyecto(proyectoDTO.getNombreProyecto());
            proyecto.setCliente(proyectoDTO.getCliente());

            // Cargar relaciones basadas en IDs
            proyecto.setFacturacionAnual(proyectoService.getFacturacionAnualById(proyectoDTO.getFacturacionAnual()));
            proyecto.setFortalezaTecnologica(proyectoService.getFortalezaTecnologicaById(proyectoDTO.getFortalezaTecnologica()));
            proyecto.setExperienciaRequerida(proyectoService.getExperienciaRequeridaById(proyectoDTO.getExperienciaRequerida()));
            proyecto.setLugarTrabajo(proyectoService.getLugarTrabajoById(proyectoDTO.getLugarTrabajo()));
            proyecto.setPrecioHora(proyectoService.getPrecioHoraById(proyectoDTO.getPrecioHora()));
            proyecto.setVolumetria(proyectoService.getVolumetriaById(proyectoDTO.getVolumetria()));

            // Cargar tecnologías basadas en IDs
            Set<Tecnologia> tecnologias = proyectoDTO.getTecnologias().stream()
                .map(proyectoService::getTecnologiaById)
                .collect(Collectors.toSet());
            proyecto.setTecnologias(tecnologias);

            // 2. Construir el JSON para el microservicio
            Map<String, Object> request = new HashMap<>();
            request.put("duracion", proyecto.getDuracion());
            request.put("presupuesto", proyecto.getPresupuesto());
            request.put("facturacion_anual", proyecto.getFacturacionAnual().getId().toString());
            request.put("fortaleza_tecnologica", proyecto.getFortalezaTecnologica().getId().toString());
            request.put("experiencia_requerida", proyecto.getExperienciaRequerida().getId().toString());
            request.put("lugar_trabajo", proyecto.getLugarTrabajo().getId().toString());
            request.put("numero_perfiles_requeridos", proyecto.getNumeroPerfilesRequeridos());
            request.put("precio_hora", proyecto.getPrecioHora().getId().toString());
            request.put("volumetria", proyecto.getVolumetria().getId().toString());
            request.put("tecnologias", proyecto.getTecnologias().stream()
                    .map(tecnologia -> tecnologia.getId().toString())
                    .collect(Collectors.toList()));

            // 3. Llamar al microservicio con el JSON generado
            ResponseEntity<Map> response = restTemplate.postForEntity(
                IA_SERVICE_URL,
                request,
                Map.class
            );

            // 4. Procesar la respuesta del microservicio
            if (response.getBody() != null && response.getBody().containsKey("prob_exito")) {
                Double porcentajeExito = Double.parseDouble(response.getBody().get("prob_exito").toString());
                proyecto.setPorcentajeExito(porcentajeExito);
            } else {
                throw new RuntimeException("El microservicio no devolvió el campo esperado 'prob_exito'");
            }

            // 5. Guardar el proyecto en la base de datos
            Proyecto proyectoGuardado = proyectoService.guardarProyecto(proyecto);
            return ResponseEntity.ok(proyectoGuardado);

        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
