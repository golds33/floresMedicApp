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

import com.tarea.floresMedicApp.dto.MedicoRequest;
import com.tarea.floresMedicApp.dto.MedicoResponse;
import com.tarea.floresMedicApp.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
	private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // POST /api/medicos
    @PostMapping
    public ResponseEntity<MedicoResponse> createMedico(@Valid @RequestBody MedicoRequest request) {
        MedicoResponse newMedico = medicoService.createMedico(request);
        return new ResponseEntity<>(newMedico, HttpStatus.CREATED);
    }

    // GET /api/medicos
    @GetMapping
    public ResponseEntity<List<MedicoResponse>> getAllMedicos() {
        List<MedicoResponse> medicos = medicoService.getAllMedicos();
        return ResponseEntity.ok(medicos);
    }

    // GET /api/medicos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> getMedicoById(@PathVariable Long id) {
        MedicoResponse medico = medicoService.getMedicoById(id);
        return ResponseEntity.ok(medico);
    }

    // GET /api/medicos/search?numeroColegiatura=ABC12345
    @GetMapping("/search")
    public ResponseEntity<MedicoResponse> getMedicoByNumeroColegiatura(@RequestParam String numeroColegiatura) {
        MedicoResponse medico = medicoService.getMedicoByNumeroColegiatura(numeroColegiatura);
        return ResponseEntity.ok(medico);
    }

    // PUT /api/medicos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> updateMedico(@PathVariable Long id, @Valid @RequestBody MedicoRequest request) {
        MedicoResponse updatedMedico = medicoService.updateMedico(id, request);
        return ResponseEntity.ok(updatedMedico);
    }

    // DELETE /api/medicos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        medicoService.deleteMedico(id);
        return ResponseEntity.noContent().build();
    }
}
