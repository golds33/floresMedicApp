package com.tarea.floresMedicApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tarea.floresMedicApp.dto.AtencionPacienteRequest;
import com.tarea.floresMedicApp.dto.AtencionPacienteResponse;
import com.tarea.floresMedicApp.dto.MedicoResponse;
import com.tarea.floresMedicApp.dto.PacienteResponse;
import com.tarea.floresMedicApp.entity.AtencionPaciente;
import com.tarea.floresMedicApp.entity.EstadoAtencion;
import com.tarea.floresMedicApp.entity.Medico;
import com.tarea.floresMedicApp.entity.Paciente;
import com.tarea.floresMedicApp.exception.ResourceNotFoundException;
import com.tarea.floresMedicApp.repository.AtencionPacienteRepository;
import com.tarea.floresMedicApp.repository.MedicoRepository;
import com.tarea.floresMedicApp.repository.PacienteRepository;

import jakarta.transaction.Transactional;

@Service
public class AtencionPacienteService {
	 private final AtencionPacienteRepository atencionPacienteRepository;
	    private final PacienteRepository pacienteRepository;
	    private final MedicoRepository medicoRepository;

	    public AtencionPacienteService(AtencionPacienteRepository atencionPacienteRepository,
                PacienteRepository pacienteRepository,
                MedicoRepository medicoRepository) {
this.atencionPacienteRepository = atencionPacienteRepository;
this.pacienteRepository = pacienteRepository;
this.medicoRepository = medicoRepository;
}

@Transactional
public AtencionPacienteResponse createAtencionPaciente(AtencionPacienteRequest request) {
// 1. Verificar existencia de paciente y médico
Paciente paciente = pacienteRepository.findById(request.getPacienteId())
.orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + request.getPacienteId()));

Medico medico = medicoRepository.findById(request.getMedicoId())
.orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + request.getMedicoId()));

// 2. Validaciones de negocio (ej. evitar solapamientos, disponibilidad)
// Ejemplo: Chequear si el médico ya tiene una cita a esa hora
atencionPacienteRepository.findByMedicoAndFechaHoraAtencion(medico, request.getFechaHoraAtencion())
.ifPresent(atencion -> {
 throw new IllegalArgumentException("El médico ya tiene una cita programada a esta hora.");
});

// 3. Crear la entidad AtencionPaciente
AtencionPaciente atencion = AtencionPaciente.builder()
.paciente(paciente)
.medico(medico)
.fechaHoraAtencion(request.getFechaHoraAtencion())
.duracionMinutos(request.getDuracionMinutos())
.status(request.getStatus() != null ? request.getStatus() : EstadoAtencion.PROGRAMADA) // Valor por defecto
.motivo(request.getMotivo())
.build();

// 4. Guardar la entidad
AtencionPaciente savedAtencion = atencionPacienteRepository.save(atencion);

// 5. Mapear a DTO de respuesta
return mapEntityToResponseDto(savedAtencion);
}

@Transactional
public List<AtencionPacienteResponse> getAllAtenciones() {
return atencionPacienteRepository.findAll().stream()
.map(this::mapEntityToResponseDto)
.collect(Collectors.toList());
}

@Transactional
public AtencionPacienteResponse getAtencionById(Long id) {
AtencionPaciente atencion = atencionPacienteRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Atención de paciente no encontrada con ID: " + id));
return mapEntityToResponseDto(atencion);
}

@Transactional
public AtencionPacienteResponse updateAtencion(Long id, AtencionPacienteRequest request) {
AtencionPaciente atencionExistente = atencionPacienteRepository.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Atención de paciente no encontrada con ID para actualizar: " + id));

Paciente paciente = pacienteRepository.findById(request.getPacienteId())
.orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + request.getPacienteId()));

Medico medico = medicoRepository.findById(request.getMedicoId())
.orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + request.getMedicoId()));

// Validaciones de negocio (ej. evitar solapamientos para el mismo médico, excluyendo la propia cita)
atencionPacienteRepository.findByMedicoAndFechaHoraAtencion(medico, request.getFechaHoraAtencion())
.ifPresent(atencion -> {
 if (!atencion.getId().equals(id)) { // Si es un duplicado y no es la misma atención que estamos actualizando
     throw new IllegalArgumentException("El médico ya tiene una cita programada a esta hora.");
 }
});

atencionExistente.setPaciente(paciente);
atencionExistente.setMedico(medico);
atencionExistente.setFechaHoraAtencion(request.getFechaHoraAtencion());
atencionExistente.setDuracionMinutos(request.getDuracionMinutos());
atencionExistente.setStatus(request.getStatus() != null ? request.getStatus() : atencionExistente.getStatus()); // Mantener el existente si no se provee
atencionExistente.setMotivo(request.getMotivo());

AtencionPaciente updatedAtencion = atencionPacienteRepository.save(atencionExistente);
return mapEntityToResponseDto(updatedAtencion);
}

@Transactional
public void deleteAtencion(Long id) {
if (!atencionPacienteRepository.existsById(id)) {
throw new ResourceNotFoundException("Atención de paciente no encontrada con ID para eliminar: " + id);
}
atencionPacienteRepository.deleteById(id);
}

// --- Métodos Auxiliares de Mapeo ---
private AtencionPacienteResponse mapEntityToResponseDto(AtencionPaciente atencion) {
// Asegúrate de que PacienteResponse y MedicoResponse tengan sus builders implementados
PacienteResponse pacienteResponse = PacienteResponse.builder()
.id(atencion.getPaciente().getId())
.dni(atencion.getPaciente().getDni())
.nombres(atencion.getPaciente().getNombres())
.apellidos(atencion.getPaciente().getApellidos())
.fechaNacimiento(atencion.getPaciente().getFechaNacimiento())
.telefono(atencion.getPaciente().getTelefono())
.email(atencion.getPaciente().getEmail())
.build();

MedicoResponse medicoResponse = MedicoResponse.builder()
.id(atencion.getMedico().getId())
.nombres(atencion.getMedico().getNombres())
.apellidos(atencion.getMedico().getApellidos())
.especialidad(atencion.getMedico().getEspecialidad())
.numeroColegiatura(atencion.getMedico().getNumeroColegiatura())
.telefono(atencion.getMedico().getTelefono())
.email(atencion.getMedico().getEmail())
.build();

return AtencionPacienteResponse.builder()
.id(atencion.getId())
.paciente(pacienteResponse)
.medico(medicoResponse)
.fechaHoraAtencion(atencion.getFechaHoraAtencion())
.duracionMinutos(atencion.getDuracionMinutos())
.status(atencion.getStatus())
.motivo(atencion.getMotivo())
.fechaCreacion(atencion.getFechaCreacion())
.fechaActualizacion(atencion.getFechaActualizacion())
.build();
}

}
