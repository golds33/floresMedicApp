package com.tarea.floresMedicApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea.floresMedicApp.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	Optional<Paciente> findByDni(String dni);
}
