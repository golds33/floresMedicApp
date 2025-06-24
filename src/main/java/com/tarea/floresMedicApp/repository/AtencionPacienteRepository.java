package com.tarea.floresMedicApp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea.floresMedicApp.entity.AtencionPaciente;
import com.tarea.floresMedicApp.entity.Medico;
import com.tarea.floresMedicApp.entity.Paciente;

public interface AtencionPacienteRepository extends JpaRepository<AtencionPaciente, Long> {
	// Método de consulta personalizado: Encontrar todas las atenciones para un paciente específico
    List<AtencionPaciente> findByPaciente(Paciente paciente);

    // Método de consulta personalizado: Encontrar todas las atenciones para un médico específico
    List<AtencionPaciente> findByMedico(Medico medico);

    // Método de consulta personalizado: Encontrar atenciones para un médico dentro de un rango de tiempo específico
    // Esto es crucial para verificar la disponibilidad del médico y evitar superposiciones
    List<AtencionPaciente> findByMedicoAndFechaHoraAtencionBetween(
            Medico medico, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin);

    // Método de consulta personalizado: Verificar si hay alguna atención existente para un médico en una hora específica
    // Esto podría usarse para la detección exacta de colisiones de franjas horarias
    Optional<AtencionPaciente> findByMedicoAndFechaHoraAtencion(Medico medico, LocalDateTime fechaHoraAtencion);

    // Encontrar atenciones por paciente y estado
    List<AtencionPaciente> findByPacienteAndStatus(Paciente paciente, com.tarea.floresMedicApp.entity.EstadoAtencion status);

    // Encontrar atenciones por médico y estado
    List<AtencionPaciente> findByMedicoAndStatus(Medico medico, com.tarea.floresMedicApp.entity.EstadoAtencion status);

}
