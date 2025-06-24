package com.tarea.floresMedicApp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;


@Entity
@Table(name = "atenciones_pacientes")
public class AtencionPaciente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @Column(nullable = false)
    private LocalDateTime fechaHoraAtencion;

    @Column(nullable = false)
    private Integer duracionMinutos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAtencion status; // PROGRAMADA, CANCELADA, COMPLETADA, AUSENTE

    @Column(length = 500)
    private String motivo;

    @CreationTimestamp // Automáticamente setea la fecha y hora de creación
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp // Automáticamente actualiza la fecha y hora de última actualización
    @Column(nullable = false)
    private LocalDateTime fechaActualizacion;

    // Constructores
    public AtencionPaciente() {}
    private AtencionPaciente(Long id, Paciente paciente, Medico medico, LocalDateTime fechaHoraAtencion,
                             Integer duracionMinutos, EstadoAtencion status, String motivo,
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

    public AtencionPaciente(Paciente paciente, Medico medico, LocalDateTime fechaHoraAtencion,
                            Integer duracionMinutos, EstadoAtencion status, String motivo) {
        this(null, paciente, medico, fechaHoraAtencion, duracionMinutos, status, motivo, null, null);
    }


    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private Paciente paciente;
        private Medico medico;
        private LocalDateTime fechaHoraAtencion;
        private Integer duracionMinutos;
        private EstadoAtencion status;
        private String motivo;
        private LocalDateTime fechaCreacion;
        private LocalDateTime fechaActualizacion;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder paciente(Paciente paciente) { this.paciente = paciente; return this; }
        public Builder medico(Medico medico) { this.medico = medico; return this; }
        public Builder fechaHoraAtencion(LocalDateTime fechaHoraAtencion) { this.fechaHoraAtencion = fechaHoraAtencion; return this; }
        public Builder duracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; return this; }
        public Builder status(EstadoAtencion status) { this.status = status; return this; }
        public Builder motivo(String motivo) { this.motivo = motivo; return this; }
        public Builder fechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; return this; }
        public Builder fechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; return this; }
        public AtencionPaciente build() {
            return new AtencionPaciente(id, paciente, medico, fechaHoraAtencion, duracionMinutos,
                                         status, motivo, fechaCreacion, fechaActualizacion);
        }
    }

    // Getters
    public Long getId() { return id; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public LocalDateTime getFechaHoraAtencion() { return fechaHoraAtencion; }
    public Integer getDuracionMinutos() { return duracionMinutos; }
    public EstadoAtencion getStatus() { return status; }
    public String getMotivo() { return motivo; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public void setFechaHoraAtencion(LocalDateTime fechaHoraAtencion) { this.fechaHoraAtencion = fechaHoraAtencion; }
    public void setDuracionMinutos(Integer duracionMinutos) { this.duracionMinutos = duracionMinutos; }
    public void setStatus(EstadoAtencion status) { this.status = status; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    // toString() (Opcional, cuidado con ciclos infinitos en relaciones @ManyToOne)
    @Override
    public String toString() {
        return "AtencionPaciente{" +
               "id=" + id +
               ", pacienteId=" + (paciente != null ? paciente.getId() : "null") + // Evita ciclos infinitos
               ", medicoId=" + (medico != null ? medico.getId() : "null") +     // Evita ciclos infinitos
               ", fechaHoraAtencion=" + fechaHoraAtencion +
               ", duracionMinutos=" + duracionMinutos +
               ", status=" + status +
               ", motivo='" + motivo + '\'' +
               ", fechaCreacion=" + fechaCreacion +
               ", fechaActualizacion=" + fechaActualizacion +
               '}';
    }
}
