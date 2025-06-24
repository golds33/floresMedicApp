package com.tarea.floresMedicApp.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteResponse {
	private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    
    
    public PacienteResponse() {
		super();
	}

	private PacienteResponse(Long id, String dni, String nombres, String apellidos,
            LocalDate fechaNacimiento, String telefono, String email) {
this.id = id;
this.dni = dni;
this.nombres = nombres;
this.apellidos = apellidos;
this.fechaNacimiento = fechaNacimiento;
this.telefono = telefono;
this.email = email;
}

// Método estático público para iniciar la construcción
public static Builder builder() {
return new Builder();
}

// Clase anidada estática Builder
public static class Builder {
private Long id;
private String dni;
private String nombres;
private String apellidos;
private LocalDate fechaNacimiento;
private String telefono;
private String email;

// Métodos "setter" encadenables (fluent setters)
public Builder id(Long id) {
this.id = id;
return this; // <--- ¡CORREGIDO! Devuelve la instancia actual del Builder
}

public Builder dni(String dni) {
this.dni = dni;
return this; // <--- ¡CORREGIDO!
}

public Builder nombres(String nombres) {
this.nombres = nombres;
return this; // <--- ¡CORREGIDO!
}

public Builder apellidos(String apellidos) {
this.apellidos = apellidos;
return this; // <--- ¡CORREGIDO!
}

public Builder fechaNacimiento(LocalDate fechaNacimiento) {
this.fechaNacimiento = fechaNacimiento;
return this; // <--- ¡CORREGIDO!
}

public Builder telefono(String telefono) {
this.telefono = telefono;
return this; // <--- ¡CORREGIDO!
}

public Builder email(String email) {
this.email = email;
return this; // <--- ¡CORREGIDO!
}

// Método final para construir el objeto PacienteResponse
public PacienteResponse build() {
return new PacienteResponse(id, dni, nombres, apellidos,
                       fechaNacimiento, telefono, email);
}
}

// Opcional: Getters (mantenerlos si los necesitas para acceder a los datos)
public Long getId() { return id; }
public String getDni() { return dni; }
public String getNombres() { return nombres; }
public String getApellidos() { return apellidos; }
public LocalDate getFechaNacimiento() { return fechaNacimiento; }
public String getTelefono() { return telefono; }
public String getEmail() { return email; }

// Opcional: toString(), equals(), hashCode()
@Override
public String toString() {
return "PacienteResponse{" +
"id=" + id +
", dni='" + dni + '\'' +
", nombres='" + nombres + '\'' +
", apellidos='" + apellidos + '\'' +
", fechaNacimiento=" + fechaNacimiento +
", telefono='" + telefono + '\'' +
", email='" + email + '\'' +
'}';
}

public void setId(Long id) {
	this.id = id;
}

public void setDni(String dni) {
	this.dni = dni;
}

public void setNombres(String nombres) {
	this.nombres = nombres;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public void setFechaNacimiento(LocalDate fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}

public void setEmail(String email) {
	this.email = email;
}

    
}
