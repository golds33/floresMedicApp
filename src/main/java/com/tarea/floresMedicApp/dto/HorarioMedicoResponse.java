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
	    private Long medicoId; // Campo para el ID del médico
	    private String nombreMedico; // Campo para el nombre del médico
	    private DayOfWeek diaSemana;
	    private LocalTime horaInicio;
	    private LocalTime horaFin;
	    private boolean disponible; // Campo para la disponibilidad

	    // --- CONSTRUCTORES ---

	    // Constructor vacío (necesario para la instanciación con new HorarioMedicoResponse())
	    public HorarioMedicoResponse() {
	        // Puedes inicializar 'disponible' aquí si quieres un valor por defecto al crear
	        this.disponible = true;
	    }
	    public boolean isDisponible() { // Nombre de convención para booleans
	        return disponible;
	    }
	    // Setter en el DTO
	    public void setDisponible(boolean disponible) {
	        this.disponible = disponible;
	    }

	    // Constructor con todos los argumentos (útil para el Builder o si construyes objetos directamente)
	    public HorarioMedicoResponse(Long id, Long medicoId, String nombreMedico,
	                                 DayOfWeek diaSemana, LocalTime horaInicio,
	                                 LocalTime horaFin, boolean disponible) {
	        this.id = id;
	        this.medicoId = medicoId;
	        this.nombreMedico = nombreMedico;
	        this.diaSemana = diaSemana;
	        this.horaInicio = horaInicio;
	        this.horaFin = horaFin;
	        this.disponible = disponible;
	    }

	    // --- GETTERS ---
	    public Long getId() {
	        return id;
	    }

	    public Long getMedicoId() { // <-- ESTE GETTER ES NECESARIO para que setMedicoId() tenga sentido en un constructor
	        return medicoId;
	    }

	    public String getNombreMedico() { // <-- ESTE GETTER ES NECESARIO
	        return nombreMedico;
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

	    // Para campos booleanos, la convención es 'is' en lugar de 'get' para el getter.
	    // Tu servicio está llamando a 'isDisponible()', así que asegúrate de que este método exista.
	 

	    // --- SETTERS ---
	    public void setId(Long id) {
	        this.id = id;
	    }

	    public void setMedicoId(Long medicoId) { // <-- ESTE SETTER ES NECESARIO
	        this.medicoId = medicoId;
	    }

	    public void setNombreMedico(String nombreMedico) { // <-- ESTE SETTER ES NECESARIO
	        this.nombreMedico = nombreMedico;
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

	  

	    // --- toString() (Opcional, pero recomendado para depuración) ---
	    @Override
	    public String toString() {
	        return "HorarioMedicoResponse{" +
	               "id=" + id +
	               ", medicoId=" + medicoId +
	               ", nombreMedico='" + nombreMedico + '\'' +
	               ", diaSemana=" + diaSemana +
	               ", horaInicio=" + horaInicio +
	               ", horaFin=" + horaFin +
	               ", disponible=" + disponible +
	               '}';
	    }

	    // --- Builder (Opcional si lo usas, pero si no tienes Lombok, no lo necesitas para el mapeo manual) ---
	    // Si realmente quieres un builder manual, asegúrate de que sus métodos y el build()
	    // coincidan perfectamente con el constructor y los campos de esta clase.
	    // Por simplicidad, si estás mapeando manualmente en el servicio, podrías prescindir del Builder aquí.
	  
	    public static HorarioMedicoResponseBuilder builder() {
	        return new HorarioMedicoResponseBuilder();
	    }

	    public static class HorarioMedicoResponseBuilder {
	        private Long id;
	        private Long medicoId;
	        private String nombreMedico;
	        private DayOfWeek diaSemana;
	        private LocalTime horaInicio;
	        private LocalTime horaFin;
	        private boolean disponible = true;

	        public HorarioMedicoResponseBuilder id(Long id) { this.id = id; return this; }
	        public HorarioMedicoResponseBuilder medicoId(Long medicoId) { this.medicoId = medicoId; return this; }
	        public HorarioMedicoResponseBuilder nombreMedico(String nombreMedico) { this.nombreMedico = nombreMedico; return this; }
	        public HorarioMedicoResponseBuilder diaSemana(DayOfWeek diaSemana) { this.diaSemana = diaSemana; return this; }
	        public HorarioMedicoResponseBuilder horaInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; return this; }
	        public HorarioMedicoResponseBuilder horaFin(LocalTime horaFin) { this.horaFin = horaFin; return this; }
	        public HorarioMedicoResponseBuilder disponible(boolean disponible) { this.disponible = disponible; return this; }

	        public HorarioMedicoResponse build() {
	            return new HorarioMedicoResponse(id, medicoId, nombreMedico, diaSemana, horaInicio, horaFin, disponible);
	        }
	    }
	   
	    
	    
}
