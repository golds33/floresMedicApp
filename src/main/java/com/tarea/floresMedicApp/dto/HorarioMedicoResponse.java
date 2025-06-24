package com.tarea.floresMedicApp.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HorarioMedicoResponse {
	 private Long id;
	    private MedicoResponse medico; // Usamos el DTO de Medico aquí
	    private DayOfWeek diaSemana;
	    private LocalTime horaInicio;
	    private LocalTime horaFin;

	    // Constructores
	    public HorarioMedicoResponse() {}
	    private HorarioMedicoResponse(Long id, MedicoResponse medico, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFin) {
	        this.id = id;
	        this.medico = medico;
	        this.diaSemana = diaSemana;
	        this.horaInicio = horaInicio;
	        this.horaFin = horaFin;
	    }

	    // Builder
	    public static Builder builder() { return new Builder(); }
	    public static class Builder {
	        private Long id;
	        private MedicoResponse medico;
	        private DayOfWeek diaSemana;
	        private LocalTime horaInicio;
	        private LocalTime horaFin;

	        public Builder id(Long id) { this.id = id; return this; }
	        public Builder medico(MedicoResponse medico) { this.medico = medico; return this; }
	        public Builder diaSemana(DayOfWeek diaSemana) { this.diaSemana = diaSemana; return this; }
	        public Builder horaInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; return this; }
	        public Builder horaFin(LocalTime horaFin) { this.horaFin = horaFin; return this; }
	        public HorarioMedicoResponse build() {
	            return new HorarioMedicoResponse(id, medico, diaSemana, horaInicio, horaFin);
	        }
	    }

	    // Getters
	    public Long getId() { return id; }
	    public MedicoResponse getMedico() { return medico; }
	    public DayOfWeek getDiaSemana() { return diaSemana; }
	    public LocalTime getHoraInicio() { return horaInicio; }
	    public LocalTime getHoraFin() { return horaFin; }

	    // toString() (Opcional)
	    @Override
	    public String toString() {
	        return "HorarioMedicoResponse{" +
	               "id=" + id +
	               ", medico=" + medico +
	               ", diaSemana=" + diaSemana +
	               ", horaInicio=" + horaInicio +
	               ", horaFin=" + horaFin +
	               '}';
	    }
}
