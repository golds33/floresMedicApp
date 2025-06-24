package com.tarea.floresMedicApp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea.floresMedicApp.entity.AtencionPaciente;
import com.tarea.floresMedicApp.entity.EstadoAtencion;
import com.tarea.floresMedicApp.entity.Medico;
import com.tarea.floresMedicApp.entity.Paciente;

public interface AtencionPacienteRepository extends JpaRepository<AtencionPaciente, Long> {
	 // JpaRepository ya te proporciona métodos básicos.

    // Ejemplos de métodos personalizados para AtencionPaciente:
    // Buscar atenciones por paciente
    List<AtencionPaciente> findByPaciente(Paciente paciente);

    // Buscar atenciones por médico
    List<AtencionPaciente> findByMedico(Medico medico);

    // Buscar atenciones por paciente y fecha/hora (para ver citas futuras/pasadas de un paciente)
    List<AtencionPaciente> findByPacienteAndFechaHoraAtencionAfter(Paciente paciente, LocalDateTime fechaHora);
    List<AtencionPaciente> findByPacienteAndFechaHoraAtencionBefore(Paciente paciente, LocalDateTime fechaHora);

    // Buscar atenciones por médico y fecha/hora
    List<AtencionPaciente> findByMedicoAndFechaHoraAtencionBetween(Medico medico, LocalDateTime start, LocalDateTime end);

    // Buscar atenciones por estado
    List<AtencionPaciente> findByStatus(EstadoAtencion status);

    // Buscar si existe una atención para un médico en un horario específico (para evitar solapamientos)
    Optional<AtencionPaciente> findByMedicoAndFechaHoraAtencion(Medico medico, LocalDateTime fechaHoraAtencion);
}
