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

import com.tarea.floresMedicApp.dto.HorarioMedicoRequest;
import com.tarea.floresMedicApp.dto.HorarioMedicoResponse;
import com.tarea.floresMedicApp.service.HorarioMedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/horarios-medicos")
public class HorarioMedicoController {
	private final HorarioMedicoService horarioMedicoService;

    public HorarioMedicoController(HorarioMedicoService horarioMedicoService) {
        this.horarioMedicoService = horarioMedicoService;
    }

    // POST /api/horarios-medicos
    @PostMapping
    public ResponseEntity<HorarioMedicoResponse> createHorarioMedico(@Valid @RequestBody HorarioMedicoRequest request) {
        HorarioMedicoResponse newHorario = horarioMedicoService.createHorarioMedico(request);
        return new ResponseEntity<>(newHorario, HttpStatus.CREATED);
    }

    // GET /api/horarios-medicos
    @GetMapping
    public ResponseEntity<List<HorarioMedicoResponse>> getAllHorariosMedicos() {
        List<HorarioMedicoResponse> horarios = horarioMedicoService.getAllHorariosMedicos();
        return ResponseEntity.ok(horarios);
    }

    // GET /api/horarios-medicos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<HorarioMedicoResponse> getHorarioMedicoById(@PathVariable Long id) {
        HorarioMedicoResponse horario = horarioMedicoService.getHorarioMedicoById(id);
        return ResponseEntity.ok(horario);
    }

    // GET /api/horarios-medicos/medico/{medicoId}
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<HorarioMedicoResponse>> getHorariosByMedicoId(@PathVariable Long medicoId) {
        List<HorarioMedicoResponse> horarios = horarioMedicoService.getHorariosByMedicoId(medicoId);
        return ResponseEntity.ok(horarios);
    }

    // PUT /api/horarios-medicos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<HorarioMedicoResponse> updateHorarioMedico(@PathVariable Long id, @Valid @RequestBody HorarioMedicoRequest request) {
        HorarioMedicoResponse updatedHorario = horarioMedicoService.updateHorarioMedico(id, request);
        return ResponseEntity.ok(updatedHorario);
    }

    // DELETE /api/horarios-medicos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorarioMedico(@PathVariable Long id) {
        horarioMedicoService.deleteHorarioMedico(id);
        return ResponseEntity.noContent().build();
    }
}
