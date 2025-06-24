package com.tarea.floresMedicApp.dto;

import java.time.LocalDateTime;

import com.tarea.floresMedicApp.entity.EstadoAtencion;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor 
@Builder
public class AtencionPacienteRequest {
	@NotNull(message = "El ID del paciente no puede ser nulo.")
    private Long pacienteId;

    @NotNull(message = "El ID del médico no puede ser nulo.")
    private Long medicoId;

    @NotNull(message = "La fecha y hora de atención no puede ser nula.")
    @FutureOrPresent(message = "La fecha y hora de atención debe ser en el presente o futuro.")
    private LocalDateTime fechaHoraAtencion;

    @NotNull(message = "La duración en minutos no puede ser nula.")
    @Min(value = 15, message = "La duración mínima de la atención es de 15 minutos.")
    private Integer duracionMinutos;

    // Estado de atención se puede dejar como nulo o con valor por defecto en el servicio
    private EstadoAtencion status;

    @Size(max = 500, message = "El motivo de la atención no puede exceder los 500 caracteres.")
    private String motivo;

    // Constructores
    public AtencionPacienteRequest() {}
    private AtencionPacienteRequest(Long pacienteId, Long medicoId, LocalDateTime fechaHoraAtencion,
                                    Integer duracionMinutos, EstadoAtencion status, String motivo) {
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.fechaHoraAtencion = fechaHoraAtencion;
        this.duracionMinutos = duracionMinutos;
        this.status = status;
        this.motivo = motivo;
    }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long pacienteId;
        private Long medicoId;
        private LocalDateTime fechaHoraAtencion;
        private Integer duracionMinutos;
        private EstadoAtencion status;
        private String motivo;

        public Builder pacienteId(Long pacienteId) { this.pacienteId = pacienteId; return this; }
        public Builder medicoId(Long medicoId) { this.medicoId = medicoId; return this; }
        public Builder fechaHoraAtencion(LocalDateTime fechaHoraAtencion) { this.fechaHoraAtencion = fechaHoraAtencion; return this; }
        public Builder duracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; return this; }
        public Builder status(EstadoAtencion status) { this.status = status; return this; }
        public Builder motivo(String motivo) { this.motivo = motivo; return this; }
        public AtencionPacienteRequest build() {
            return new AtencionPacienteRequest(pacienteId, medicoId, fechaHoraAtencion, duracionMinutos, status, motivo);
        }
    }

    // Getters
    public Long getPacienteId() { return pacienteId; }
    public Long getMedicoId() { return medicoId; }
    public LocalDateTime getFechaHoraAtencion() { return fechaHoraAtencion; }
    public Integer getDuracionMinutos() { return duracionMinutos; }
    public EstadoAtencion getStatus() { return status; }
    public String getMotivo() { return motivo; }

    // Setters (Opcional, si necesitas modificar el request después de creado)
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public void setMedicoId(Long medicoId) { this.medicoId = medicoId; }
    public void setFechaHoraAtencion(LocalDateTime fechaHoraAtencion) { this.fechaHoraAtencion = fechaHoraAtencion; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public void setStatus(EstadoAtencion status) { this.status = status; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    // toString() (Opcional)
    @Override
    public String toString() {
        return "AtencionPacienteRequest{" +
               "pacienteId=" + pacienteId +
               ", medicoId=" + medicoId +
               ", fechaHoraAtencion=" + fechaHoraAtencion +
               ", duracionMinutos=" + duracionMinutos +
               ", status=" + status +
               ", motivo='" + motivo + '\'' +
               '}';
    }
	    
}
