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
	@NotNull(message = "El ID del médico no puede ser nulo.")
    private Long medicoId;

    @NotNull(message = "El día de la semana no puede ser nulo.")
    private DayOfWeek diaSemana; // Asumiendo que DayOfWeek es tu enum personalizado

    @NotNull(message = "La hora de inicio no puede ser nula.")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin no puede ser nula.")
    private LocalTime horaFin;

    // Constructores
    public HorarioMedicoRequest() {}
    private HorarioMedicoRequest(Long medicoId, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.medicoId = medicoId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long medicoId;
        private DayOfWeek diaSemana;
        private LocalTime horaInicio;
        private LocalTime horaFin;

        public Builder medicoId(Long medicoId) { this.medicoId = medicoId; return this; }
        public Builder diaSemana(DayOfWeek diaSemana) { this.diaSemana = diaSemana; return this; }
        public Builder horaInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; return this; }
        public Builder horaFin(LocalTime horaFin) { this.horaFin = horaFin; return this; }
        public HorarioMedicoRequest build() {
            return new HorarioMedicoRequest(medicoId, diaSemana, horaInicio, horaFin);
        }
    }

    // Getters
    public Long getMedicoId() { return medicoId; }
    public DayOfWeek getDiaSemana() { return diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }

    // Setters (Opcional)
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public void setDiaSemana(DayOfWeek diaSemana) { this.diaSemana = diaSemana; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    // toString() (Opcional)
    @Override
    public String toString() {
        return "HorarioMedicoRequest{" +
               "medicoId=" + medicoId +
               ", diaSemana=" + diaSemana +
               ", horaInicio=" + horaInicio +
               ", horaFin=" + horaFin +
               '}';
    }

    
}
