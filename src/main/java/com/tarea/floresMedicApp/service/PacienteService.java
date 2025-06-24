package com.tarea.floresMedicApp.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarea.floresMedicApp.dto.PacienteRequest;
import com.tarea.floresMedicApp.dto.PacienteResponse;
import com.tarea.floresMedicApp.entity.Paciente;
import com.tarea.floresMedicApp.exception.ResourceNotFoundException;
import com.tarea.floresMedicApp.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    // Inyección de dependencias por constructor (preferida sobre @Autowired en campos)
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional // Marca el método como transaccional
    public PacienteResponse createPaciente(PacienteRequest request) {
        // 1. Convertir DTO de solicitud a entidad Paciente
        Paciente paciente = Paciente.builder()
                .dni(request.getDni())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .fechaNacimiento(request.getFechaNacimiento())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .build();

        // 2. Guardar la entidad en la base de datos
        Paciente savedPaciente = pacienteRepository.save(paciente);

        // 3. Convertir la entidad guardada a DTO de respuesta y devolverlo
        return PacienteResponse.builder()
                .id(savedPaciente.getId())
                .dni(savedPaciente.getDni())
                .nombres(savedPaciente.getNombres())
                .apellidos(savedPaciente.getApellidos())
                .fechaNacimiento(savedPaciente.getFechaNacimiento())
                .telefono(savedPaciente.getTelefono())
                .email(savedPaciente.getEmail())
                .build();
    }

    // Transacción de solo lectura para mejor rendimiento
    public List<PacienteResponse> getAllPacientes() {
        return pacienteRepository.findAll().stream()
                .map(this::mapEntityToResponseDto) // Usa un método auxiliar para mapear
                .collect(Collectors.toList());
    }

    @Transactional
    public PacienteResponse getPacienteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));
        return mapEntityToResponseDto(paciente);
    }

    @Transactional
    public PacienteResponse getPacienteByDni(String dni) {
        Paciente paciente = pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con DNI: " + dni));
        return mapEntityToResponseDto(paciente);
    }

    @Transactional
    public PacienteResponse updatePaciente(Long id, PacienteRequest request) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID para actualizar: " + id));

        // Actualizar los campos de la entidad existente con los datos del request
        pacienteExistente.setDni(request.getDni());
        pacienteExistente.setNombres(request.getNombres());
        pacienteExistente.setApellidos(request.getApellidos());
        pacienteExistente.setFechaNacimiento(request.getFechaNacimiento());
        pacienteExistente.setTelefono(request.getTelefono());
        pacienteExistente.setEmail(request.getEmail());

        // Guardar la entidad actualizada (Spring Data JPA detecta los cambios y los persiste)
        Paciente updatedPaciente = pacienteRepository.save(pacienteExistente);

        // Convertir y devolver el DTO de respuesta
        return mapEntityToResponseDto(updatedPaciente);
    }

    @Transactional
    public void deletePaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente no encontrado con ID para eliminar: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    // --- Métodos Auxiliares de Mapeo ---
    private PacienteResponse mapEntityToResponseDto(Paciente paciente) {
        return PacienteResponse.builder()
                .id(paciente.getId())
                .dni(paciente.getDni())
                .nombres(paciente.getNombres())
                .apellidos(paciente.getApellidos())
                .fechaNacimiento(paciente.getFechaNacimiento())
                .telefono(paciente.getTelefono())
                .email(paciente.getEmail())
                .build();
    }
}

