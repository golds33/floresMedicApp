package com.tarea.floresMedicApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea.floresMedicApp.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long>{
	Optional<Paciente> findByDni(String dni); // Este ya lo tenías

    // Métodos que necesitas agregar:

    /**
     * Busca un paciente por su dirección de correo electrónico.
     * @param email La dirección de correo electrónico del paciente.
     * @return Un Optional que contiene el Paciente si se encuentra, o vacío si no.
     */
    Optional<Paciente> findByEmail(String email);

    /**
     * Verifica si existe un paciente con el DNI especificado.
     * @param dni El DNI a verificar.
     * @return true si existe un paciente con ese DNI, false en caso contrario.
     */
    boolean existsByDni(String dni);

    /**
     * Verifica si existe un paciente con la dirección de correo electrónico especificada.
     * @param email La dirección de correo electrónico a verificar.
     * @return true si existe un paciente con ese email, false en caso contrario.
     */
    boolean existsByEmail(String email);
}
