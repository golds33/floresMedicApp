package com.tarea.floresMedicApp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarea.floresMedicApp.dto.AtencionPacienteRequest;
import com.tarea.floresMedicApp.dto.AtencionPacienteResponse;
import com.tarea.floresMedicApp.service.AtencionPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/atenciones")
public class AtencionPacienteController {
	private final AtencionPacienteService atencionPacienteService;

    public AtencionPacienteController(AtencionPacienteService atencionPacienteService) {
        this.atencionPacienteService = atencionPacienteService;
    }

    // POST /api/atenciones
    @PostMapping
    public ResponseEntity<AtencionPacienteResponse> createAtencionPaciente(@Valid @RequestBody AtencionPacienteRequest request) {
        AtencionPacienteResponse newAtencion = atencionPacienteService.createAtencionPaciente(request);
        return new ResponseEntity<>(newAtencion, HttpStatus.CREATED);
    }

    // GET /api/atenciones
    @GetMapping
    public ResponseEntity<List<AtencionPacienteResponse>> getAllAtenciones() {
        List<AtencionPacienteResponse> atenciones = atencionPacienteService.getAllAtenciones();
        return ResponseEntity.ok(atenciones);
    }

    // GET /api/atenciones/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AtencionPacienteResponse> getAtencionById(@PathVariable Long id) {
        AtencionPacienteResponse atencion = atencionPacienteService.getAtencionById(id);
        return ResponseEntity.ok(atencion);
    }

    // PUT /api/atenciones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AtencionPacienteResponse> updateAtencion(@PathVariable Long id, @Valid @RequestBody AtencionPacienteRequest request) {
        AtencionPacienteResponse updatedAtencion = atencionPacienteService.updateAtencion(id, request);
        return ResponseEntity.ok(updatedAtencion);
    }

    // DELETE /api/atenciones/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtencion(@PathVariable Long id) {
        atencionPacienteService.deleteAtencion(id);
        return ResponseEntity.noContent().build();
    }
}
