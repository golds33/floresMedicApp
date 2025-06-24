package com.tarea.floresMedicApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea.floresMedicApp.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	Optional<Medico> findByNumeroColegiatura(String numeroColegiatura);
}
