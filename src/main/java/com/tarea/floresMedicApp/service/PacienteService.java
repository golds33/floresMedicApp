package com.tarea.floresMedicApp.service;





import com.tarea.floresMedicApp.dto.PacienteRequest;
import com.tarea.floresMedicApp.dto.PacienteResponse;
import com.tarea.floresMedicApp.entity.Paciente;
import com.tarea.floresMedicApp.exception.ResourceNotFoundException; // Asegúrate de tener esta clase de excepción
import com.tarea.floresMedicApp.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importación correcta para @Transactional

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    // Inyección de dependencias por constructor
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Transactional // Marca el método como transaccional
    public PacienteResponse createPaciente(PacienteRequest request) {
        // 1. Validar si ya existe un paciente con el mismo DNI o Email (si son únicos)
        if (pacienteRepository.findByDni(request.getDni()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un paciente con el DNI: " + request.getDni());
        }
        if (pacienteRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un paciente con el email: " + request.getEmail());
        }

        // 2. Convertir DTO de solicitud a entidad Paciente (¡USANDO CONSTRUCTOR O SETTERS MANUALMENTE!)
        Paciente paciente = new Paciente(); // Usa el constructor vacío
        paciente.setDni(request.getDni());
        paciente.setNombres(request.getNombres());
        paciente.setApellidos(request.getApellidos());
        // Asegúrate de que fechaNacimiento exista en PacienteRequest y Paciente.java
        paciente.setFechaNacimiento(request.getFechaNacimiento());
        paciente.setTelefono(request.getTelefono());
        paciente.setEmail(request.getEmail());

        // 3. Guardar la entidad en la base de datos
        Paciente savedPaciente = pacienteRepository.save(paciente);

        // 4. Convertir la entidad guardada a DTO de respuesta y devolverlo (¡USANDO SETTERS MANUALMENTE!)
        PacienteResponse response = new PacienteResponse();
        response.setId(savedPaciente.getId());
        response.setDni(savedPaciente.getDni());
        response.setNombres(savedPaciente.getNombres());
        response.setApellidos(savedPaciente.getApellidos());
        response.setFechaNacimiento(savedPaciente.getFechaNacimiento());
        response.setTelefono(savedPaciente.getTelefono());
        response.setEmail(savedPaciente.getEmail());
        return response;
    }

    @Transactional(readOnly = true) // ¡CORREGIDO! Añadido @Transactional(readOnly = true)
    public List<PacienteResponse> getAllPacientes() {
        return pacienteRepository.findAll().stream()
                .map(this::mapEntityToResponseDto) // Usa un método auxiliar para mapear
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true) // ¡CORREGIDO! Añadido @Transactional(readOnly = true)
    public PacienteResponse getPacienteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + id));
        return mapEntityToResponseDto(paciente);
    }

    @Transactional(readOnly = true) // ¡CORREGIDO! Añadido @Transactional(readOnly = true)
    public PacienteResponse getPacienteByDni(String dni) {
        Paciente paciente = pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con DNI: " + dni));
        return mapEntityToResponseDto(paciente);
    }

    @Transactional
    public PacienteResponse updatePaciente(Long id, PacienteRequest request) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID para actualizar: " + id));

        // Validar si el DNI o Email se cambian y ya existen en otro paciente
        if (!pacienteExistente.getDni().equals(request.getDni()) && pacienteRepository.findByDni(request.getDni()).isPresent()) {
            throw new IllegalArgumentException("Ya existe otro paciente con el DNI: " + request.getDni());
        }
        if (!pacienteExistente.getEmail().equals(request.getEmail()) && pacienteRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe otro paciente con el email: " + request.getEmail());
        }

        // Actualizar los campos de la entidad existente con los datos del request
        pacienteExistente.setDni(request.getDni());
        pacienteExistente.setNombres(request.getNombres());
        pacienteExistente.setApellidos(request.getApellidos());
        // Asegúrate de que fechaNacimiento exista en PacienteRequest y Paciente.java
        pacienteExistente.setFechaNacimiento(request.getFechaNacimiento());
        pacienteExistente.setTelefono(request.getTelefono());
        pacienteExistente.setEmail(request.getEmail());

        // Guardar la entidad actualizada (Spring Data JPA detecta los cambios y los persiste)
        Paciente updatedPaciente = pacienteRepository.save(pacienteExistente);

        // Convertir y devolver el DTO de respuesta (¡USANDO SETTERS MANUALMENTE!)
        return mapEntityToResponseDto(updatedPaciente);
    }

    @Transactional
    public void deletePaciente(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente no encontrado con ID para eliminar: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    // --- Métodos Auxiliares de Mapeo (¡USANDO SETTERS MANUALMENTE!) ---
    private PacienteResponse mapEntityToResponseDto(Paciente paciente) {
        PacienteResponse response = new PacienteResponse();
        response.setId(paciente.getId());
        response.setDni(paciente.getDni());
        response.setNombres(paciente.getNombres());
        response.setApellidos(paciente.getApellidos());
        response.setFechaNacimiento(paciente.getFechaNacimiento());
        response.setTelefono(paciente.getTelefono());
        response.setEmail(paciente.getEmail());
        return response;
    }
}

