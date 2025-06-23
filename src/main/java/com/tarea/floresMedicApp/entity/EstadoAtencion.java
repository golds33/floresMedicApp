package com.tarea.floresMedicApp.entity;

public enum EstadoAtencion {
	 PROGRAMADA,   // Cita programada
	    COMPLETADA,   // Cita completada
	    CANCELADA,    // Cita cancelada por el paciente o clínica
	    REPROGRAMADA, // Cita reprogramada
	    AUSENTE      // Paciente no se presentó (No-show)
}
