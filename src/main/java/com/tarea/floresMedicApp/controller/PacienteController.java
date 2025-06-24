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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tarea.floresMedicApp.dto.PacienteRequest;
import com.tarea.floresMedicApp.dto.PacienteResponse;
import com.tarea.floresMedicApp.service.PacienteService;

import jakarta.validation.Valid;

@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    // Inyección de dependencias por constructor
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Endpoint para crear un nuevo paciente
    // POST /api/pacientes
    @PostMapping
    public ResponseEntity<PacienteResponse> createPaciente(@Valid @RequestBody PacienteRequest request) {
        PacienteResponse newPaciente = pacienteService.createPaciente(request);
        return new ResponseEntity<>(newPaciente, HttpStatus.CREATED); // Retorna 201 Created
    }

    // Endpoint para obtener todos los pacientes
    // GET /api/pacientes
    @GetMapping
    public ResponseEntity<List<PacienteResponse>> getAllPacientes() {
        List<PacienteResponse> pacientes = pacienteService.getAllPacientes();
        return ResponseEntity.ok(pacientes); // Retorna 200 OK
    }

    // Endpoint para obtener un paciente por su ID
    // GET /api/pacientes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> getPacienteById(@PathVariable Long id) {
        PacienteResponse paciente = pacienteService.getPacienteById(id);
        return ResponseEntity.ok(paciente); // Retorna 200 OK
    }

    // Endpoint para obtener un paciente por su DNI (ejemplo de búsqueda por parámetro de consulta)
    // GET /api/pacientes/search?dni=12345678
    @GetMapping("/search")
    public ResponseEntity<PacienteResponse> getPacienteByDni(@RequestParam String dni) {
        PacienteResponse paciente = pacienteService.getPacienteByDni(dni);
        return ResponseEntity.ok(paciente); // Retorna 200 OK
    }

    // Endpoint para actualizar un paciente existente
    // PUT /api/pacientes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> updatePaciente(@PathVariable Long id, @Valid @RequestBody PacienteRequest request) {
        PacienteResponse updatedPaciente = pacienteService.updatePaciente(id, request);
        return ResponseEntity.ok(updatedPaciente); // Retorna 200 OK
    }

    // Endpoint para eliminar un paciente
    // DELETE /api/pacientes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
