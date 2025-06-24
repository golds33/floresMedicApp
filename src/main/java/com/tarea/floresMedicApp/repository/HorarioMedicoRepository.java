package com.tarea.floresMedicApp.repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea.floresMedicApp.entity.HorarioMedico;
import com.tarea.floresMedicApp.entity.Medico;

public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico, Long>{
	List<HorarioMedico> findByMedico(Medico medico);

    // Buscar horarios por médico y día de la semana
    List<HorarioMedico> findByMedicoAndDiaSemana(Medico medico, DayOfWeek diaSemana);

    // Buscar un horario específico por médico, día, hora de inicio y fin (para verificar duplicados)
    Optional<HorarioMedico> findByMedicoAndDiaSemanaAndHoraInicioAndHoraFin(
            Medico medico, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFin);
}
