package com.tarea.floresMedicApp.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioMedicoRequest {
	@NotNull(message = "El ID del médico no puede ser nulo")
    private Long medicoId;

    @NotNull(message = "El día de la semana no puede ser nulo")
    private DayOfWeek diaSemana; // O String, si no usas un Enum DayOfWeek

    @NotNull(message = "La hora de inicio no puede ser nula")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin no puede ser nula")
    private LocalTime horaFin;

    private boolean disponible; // No asignar valor por defecto aquí, se hace en el constructor o setter

    // --- CONSTRUCTORES ---

    // Constructor vacío
    public HorarioMedicoRequest() {
        // Puedes establecer un valor por defecto para 'disponible' aquí si lo deseas
        this.disponible = true;
    }

    // Constructor con todos los argumentos (incluyendo disponible)
    public HorarioMedicoRequest(Long medicoId, DayOfWeek diaSemana, LocalTime horaInicio,
                                LocalTime horaFin, boolean disponible) {
        this.medicoId = medicoId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponible = disponible;
    }

    // --- GETTERS ---
    public Long getMedicoId() {
        return medicoId;
    }

    public DayOfWeek getDiaSemana() {
        return diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    // Getter para booleanos usa 'is' por convención
    public boolean isDisponible() {
        return disponible;
    }

    // --- SETTERS ---
    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public void setDiaSemana(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // --- toString() ---
    @Override
    public String toString() {
        return "HorarioMedicoRequest{" +
               "medicoId=" + medicoId +
               ", diaSemana=" + diaSemana +
               ", horaInicio=" + horaInicio +
               ", horaFin=" + horaFin +
               ", disponible=" + disponible +
               '}';
    }

    // --- BUILDER (Implementación manual) ---
    public static HorarioMedicoRequestBuilder builder() {
        return new HorarioMedicoRequestBuilder();
    }

    public static class HorarioMedicoRequestBuilder {
        private Long medicoId;
        private DayOfWeek diaSemana;
        private LocalTime horaInicio;
        private LocalTime horaFin;
        private boolean disponible = true; // Valor por defecto para el builder

        // El builder generalmente no tiene constructores con argumentos
        // Los campos se asignan mediante los métodos encadenados (medicoId(), diaSemana(), etc.)

        public HorarioMedicoRequestBuilder medicoId(Long medicoId) {
            this.medicoId = medicoId;
            return this;
        }

        public HorarioMedicoRequestBuilder diaSemana(DayOfWeek diaSemana) {
            this.diaSemana = diaSemana;
            return this;
        }

        public HorarioMedicoRequestBuilder horaInicio(LocalTime horaInicio) {
            this.horaInicio = horaInicio;
            return this;
        }

        public HorarioMedicoRequestBuilder horaFin(LocalTime horaFin) {
            this.horaFin = horaFin;
            return this;
        }

        public HorarioMedicoRequestBuilder disponible(boolean disponible) {
            this.disponible = disponible;
            return this;
        }

        public HorarioMedicoRequest build() {
            return new HorarioMedicoRequest(medicoId, diaSemana, horaInicio, horaFin, disponible);
        }
    }
    
}
