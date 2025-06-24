package com.tarea.floresMedicApp.dto;

import java.time.LocalDateTime;

import com.tarea.floresMedicApp.entity.EstadoAtencion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtencionPacienteResponse {
	 private Long id;
	    private PacienteResponse paciente; // Usamos el DTO de Paciente aquí
	    private MedicoResponse medico;     // Usamos el DTO de Medico aquí
	    private LocalDateTime fechaHoraAtencion;
	    private Integer duracionMinutos;
	    private EstadoAtencion status;
	    private String motivo;
	    private LocalDateTime fechaCreacion;
	    private LocalDateTime fechaActualizacion;

	    // Constructores
	    public AtencionPacienteResponse() {}
	    private AtencionPacienteResponse(Long id, PacienteResponse paciente, MedicoResponse medico,
	                                     LocalDateTime fechaHoraAtencion, Integer duracionMinutos,
	                                     EstadoAtencion status, String motivo,
	                                     LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
	        this.id = id;
	        this.paciente = paciente;
	        this.medico = medico;
	        this.fechaHoraAtencion = fechaHoraAtencion;
	        this.duracionMinutos = duracionMinutos;
	        this.status = status;
	        this.motivo = motivo;
	        this.fechaCreacion = fechaCreacion;
	        this.fechaActualizacion = fechaActualizacion;
	    }

	    // Builder
	    public static Builder builder() { return new Builder(); }
	    public static class Builder {
	        private Long id;
	        private PacienteResponse paciente;
	        private MedicoResponse medico;
	        private LocalDateTime fechaHoraAtencion;
	        private Integer duracionMinutos;
	        private EstadoAtencion status;
	        private String motivo;
	        private LocalDateTime fechaCreacion;
	        private LocalDateTime fechaActualizacion;

	        public Builder id(Long id) { this.id = id; return this; }
	        public Builder paciente(PacienteResponse paciente) { this.paciente = paciente; return this; }
	        public Builder medico(MedicoResponse medico) { this.medico = medico; return this; }
	        public Builder fechaHoraAtencion(LocalDateTime fechaHoraAtencion) { this.fechaHoraAtencion = fechaHoraAtencion; return this; }
	        public Builder duracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; return this; }
	        public Builder status(EstadoAtencion status) { this.status = status; return this; }
	        public Builder motivo(String motivo) { this.motivo = motivo; return this; }
	        public Builder fechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; return this; }
	        public Builder fechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; return this; }
	        public AtencionPacienteResponse build() {
	            return new AtencionPacienteResponse(id, paciente, medico, fechaHoraAtencion, duracionMinutos,
	                                                 status, motivo, fechaCreacion, fechaActualizacion);
	        }
	    }

	    // Getters
	    public Long getId() { return id; }
	    public PacienteResponse getPaciente() { return paciente; }
	    public MedicoResponse getMedico() { return medico; }
	    public LocalDateTime getFechaHoraAtencion() { return fechaHoraAtencion; }
	    public Integer getDuracionMinutos() { return duracionMinutos; }
	    public EstadoAtencion getStatus() { return status; }
	    public String getMotivo() { return motivo; }
	    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
	    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }

	    // toString() (Opcional)
	    @Override
	    public String toString() {
	        return "AtencionPacienteResponse{" +
	               "id=" + id +
	               ", paciente=" + paciente +
	               ", medico=" + medico +
	               ", fechaHoraAtencion=" + fechaHoraAtencion +
	               ", duracionMinutos=" + duracionMinutos +
	               ", status=" + status +
	               ", motivo='" + motivo + '\'' +
	               ", fechaCreacion=" + fechaCreacion +
	               ", fechaActualizacion=" + fechaActualizacion +
	               '}';
	    }
}
