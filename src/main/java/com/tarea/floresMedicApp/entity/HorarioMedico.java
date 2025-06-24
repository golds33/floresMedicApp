package com.tarea.floresMedicApp.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "horarios_medicos", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"medico_id", "diaSemana"}) // A doctor can only have one schedule per day
})
public class HorarioMedico {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    // Relación ManyToOne con Medico: Muchos HorariosMedicos para un solo Medico
	    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading es mejor para rendimiento
	    @JoinColumn(name = "medico_id", nullable = false) // Columna FK en esta tabla (horarios_medicos)
	    private Medico medico; // El objeto Medico completo

	    @Enumerated(EnumType.STRING) // Guarda el nombre del enum (ej. "LUNES") en la DB
	    @Column(nullable = false)
	    private DayOfWeek diaSemana; // O String, si no usas un Enum DayOfWeek

	    @Column(nullable = false)
	    private LocalTime horaInicio; // Usar LocalTime para horas

	    @Column(nullable = false)
	    private LocalTime horaFin; // Usar LocalTime para horas

	    @Column(nullable = false)
	    private boolean disponible; // Para indicar si el horario está activo o no
	    
        
        public boolean isDisponible() { // Nombre de convención para booleans
            return disponible;
        }
        // Setter en la entidad (opcional si solo lees desde el DTO, pero buena práctica)
        public void setDisponible(boolean disponible) {
            this.disponible = disponible;
        }
	
    // Constructores
    public HorarioMedico() {}
    private HorarioMedico(Long id, Medico medico, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.medico = medico;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public HorarioMedico(Medico medico, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        this(null, medico, diaSemana, horaInicio, horaFin);
    }


    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id;
        private Medico medico;
        private DayOfWeek diaSemana;
        private LocalTime horaInicio;
        private LocalTime horaFin;
        

        public Builder id(Long id) { this.id = id; return this; }
        public Builder medico(Medico medico) { this.medico = medico; return this; }
        public Builder diaSemana(DayOfWeek diaSemana) { this.diaSemana = diaSemana; return this; }
        public Builder horaInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; return this; }
        public Builder horaFin(LocalTime horaFin) { this.horaFin = horaFin; return this; }
        public HorarioMedico build() {
            return new HorarioMedico(id, medico, diaSemana, horaInicio, horaFin);
        }
    }

    // Getters
    public Long getId() { return id; }
    public Medico getMedico() { return medico; }
    public DayOfWeek getDiaSemana() { return diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public void setDiaSemana(DayOfWeek diaSemana) { this.diaSemana = diaSemana; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    // toString() (Opcional)
    @Override
    public String toString() {
        return "HorarioMedico{" +
               "id=" + id +
               ", medico=" + medico.getId() + // Evita bucle infinito si Medico tiene HorarioMedico
               ", diaSemana=" + diaSemana +
               ", horaInicio=" + horaInicio +
               ", horaFin=" + horaFin +
               '}';
    }

   
}
