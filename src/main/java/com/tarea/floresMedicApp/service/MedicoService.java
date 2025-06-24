package com.tarea.floresMedicApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarea.floresMedicApp.dto.MedicoRequest;
import com.tarea.floresMedicApp.dto.MedicoResponse;
import com.tarea.floresMedicApp.entity.Medico;
import com.tarea.floresMedicApp.exception.ResourceNotFoundException;
import com.tarea.floresMedicApp.repository.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicoService {
	 private final MedicoRepository medicoRepository;

	    public MedicoService(MedicoRepository medicoRepository) {
	        this.medicoRepository = medicoRepository;
	    }

	    @Transactional
	    public MedicoResponse createMedico(MedicoRequest request) {
	        Medico medico = Medico.builder()
	                .nombres(request.getNombres())
	                .apellidos(request.getApellidos())
	                .especialidad(request.getEspecialidad())
	                .numeroColegiatura(request.getNumeroColegiatura())
	                .telefono(request.getTelefono())
	                .email(request.getEmail())
	                .build();

	        Medico savedMedico = medicoRepository.save(medico);
	        return mapEntityToResponseDto(savedMedico);
	    }

	    @Transactional
	    public List<MedicoResponse> getAllMedicos() {
	        return medicoRepository.findAll().stream()
	                .map(this::mapEntityToResponseDto)
	                .collect(Collectors.toList());
	    }

	    @Transactional
	    public MedicoResponse getMedicoById(Long id) {
	        Medico medico = medicoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + id));
	        return mapEntityToResponseDto(medico);
	    }

	    @Transactional
	    public MedicoResponse getMedicoByNumeroColegiatura(String numeroColegiatura) {
	        Medico medico = medicoRepository.findByNumeroColegiatura(numeroColegiatura)
	                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con número de colegiatura: " + numeroColegiatura));
	        return mapEntityToResponseDto(medico);
	    }

	    @Transactional
	    public MedicoResponse updateMedico(Long id, MedicoRequest request) {
	        Medico medicoExistente = medicoRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID para actualizar: " + id));

	        medicoExistente.setNombres(request.getNombres());
	        medicoExistente.setApellidos(request.getApellidos());
	        medicoExistente.setEspecialidad(request.getEspecialidad());
	        medicoExistente.setNumeroColegiatura(request.getNumeroColegiatura());
	        medicoExistente.setTelefono(request.getTelefono());
	        medicoExistente.setEmail(request.getEmail());

	        Medico updatedMedico = medicoRepository.save(medicoExistente);
	        return mapEntityToResponseDto(updatedMedico);
	    }

	    @Transactional
	    public void deleteMedico(Long id) {
	        if (!medicoRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Médico no encontrado con ID para eliminar: " + id);
	        }
	        medicoRepository.deleteById(id);
	    }

	    // --- Métodos Auxiliares de Mapeo ---
	    private MedicoResponse mapEntityToResponseDto(Medico medico) {
	        return MedicoResponse.builder()
	                .id(medico.getId())
	                .nombres(medico.getNombres())
	                .apellidos(medico.getApellidos())
	                .especialidad(medico.getEspecialidad())
	                .numeroColegiatura(medico.getNumeroColegiatura())
	                .telefono(medico.getTelefono())
	                .email(medico.getEmail())
	                .build();
	    }
}
