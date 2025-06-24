package com.tarea.floresMedicApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarea.floresMedicApp.dto.HorarioMedicoRequest;
import com.tarea.floresMedicApp.dto.HorarioMedicoResponse;
import com.tarea.floresMedicApp.dto.MedicoResponse;
import com.tarea.floresMedicApp.entity.HorarioMedico;
import com.tarea.floresMedicApp.entity.Medico;
import com.tarea.floresMedicApp.exception.ResourceNotFoundException;
import com.tarea.floresMedicApp.repository.HorarioMedicoRepository;
import com.tarea.floresMedicApp.repository.MedicoRepository;

import jakarta.transaction.Transactional;

@Service
public class HorarioMedicoService {
	private final HorarioMedicoRepository horarioMedicoRepository;
    private final MedicoRepository medicoRepository; // Necesitamos el repositorio de Medico

    public HorarioMedicoService(HorarioMedicoRepository horarioMedicoRepository, MedicoRepository medicoRepository) {
        this.horarioMedicoRepository = horarioMedicoRepository;
        this.medicoRepository = medicoRepository;
    }

    @Transactional
    public HorarioMedicoResponse createHorarioMedico(HorarioMedicoRequest request) {
        // 1. Buscar el médico asociado
        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + request.getMedicoId()));

        // 2. Verificar si ya existe un horario idéntico para este médico
        horarioMedicoRepository.findByMedicoAndDiaSemanaAndHoraInicioAndHoraFin(
                medico, request.getDiaSemana(), request.getHoraInicio(), request.getHoraFin())
                .ifPresent(h -> {
                    throw new IllegalArgumentException("Ya existe un horario idéntico para este médico en este día y hora.");
                });

        // 3. Crear la entidad HorarioMedico
        HorarioMedico horarioMedico = HorarioMedico.builder()
                .medico(medico)
                .diaSemana(request.getDiaSemana())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .build();

        // 4. Guardar la entidad
        HorarioMedico savedHorario = horarioMedicoRepository.save(horarioMedico);

        // 5. Mapear a DTO de respuesta
        return mapEntityToResponseDto(savedHorario);
    }

    @Transactional
    public List<HorarioMedicoResponse> getAllHorariosMedicos() {
        return horarioMedicoRepository.findAll().stream()
                .map(this::mapEntityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public HorarioMedicoResponse getHorarioMedicoById(Long id) {
        HorarioMedico horarioMedico = horarioMedicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario médico no encontrado con ID: " + id));
        return mapEntityToResponseDto(horarioMedico);
    }

    @Transactional
    public List<HorarioMedicoResponse> getHorariosByMedicoId(Long medicoId) {
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + medicoId));

        return horarioMedicoRepository.findByMedico(medico).stream()
                .map(this::mapEntityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public HorarioMedicoResponse updateHorarioMedico(Long id, HorarioMedicoRequest request) {
        HorarioMedico horarioExistente = horarioMedicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Horario médico no encontrado con ID para actualizar: " + id));

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + request.getMedicoId()));

        // Verificar si la actualización resultaría en un duplicado (excepto si es el mismo horario)
        horarioMedicoRepository.findByMedicoAndDiaSemanaAndHoraInicioAndHoraFin(
                medico, request.getDiaSemana(), request.getHoraInicio(), request.getHoraFin())
                .ifPresent(h -> {
                    if (!h.getId().equals(id)) { // Si es un duplicado y no es el mismo horario que estamos actualizando
                        throw new IllegalArgumentException("Ya existe un horario idéntico para este médico en este día y hora.");
                    }
                });

        horarioExistente.setMedico(medico);
        horarioExistente.setDiaSemana(request.getDiaSemana());
        horarioExistente.setHoraInicio(request.getHoraInicio());
        horarioExistente.setHoraFin(request.getHoraFin());

        HorarioMedico updatedHorario = horarioMedicoRepository.save(horarioExistente);
        return mapEntityToResponseDto(updatedHorario);
    }

    @Transactional
    public void deleteHorarioMedico(Long id) {
        if (!horarioMedicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Horario médico no encontrado con ID para eliminar: " + id);
        }
        horarioMedicoRepository.deleteById(id);
    }

    // --- Métodos Auxiliares de Mapeo ---
    private HorarioMedicoResponse mapEntityToResponseDto(HorarioMedico horario) {
        HorarioMedicoResponse response = new HorarioMedicoResponse(); // Instanciar manualmente el DTO
        
        response.setId(horario.getId());
        
        // Es buena práctica verificar si el objeto Medico no es nulo antes de acceder a sus propiedades
        if (horario.getMedico() != null) {
            response.setMedicoId(horario.getMedico().getId()); // Llamando al setter del DTO
            response.setNombreMedico(horario.getMedico().getNombres() + " " + horario.getMedico().getApellidos()); // Llamando al setter del DTO
        } else {
            // Manejar el caso si el médico es nulo (aunque con nullable=false en la entidad no debería pasar)
            response.setMedicoId(null);
            response.setNombreMedico("Médico Desconocido");
        }
        
        response.setDiaSemana(horario.getDiaSemana());
        response.setHoraInicio(horario.getHoraInicio());
        response.setHoraFin(horario.getHoraFin());
        response.setDisponible(horario.isDisponible()); // Llamando al setter del DTO y al getter del Entity

        return response;
    }

}
