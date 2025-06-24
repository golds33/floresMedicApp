package com.tarea.floresMedicApp.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor // Constructor sin argumentos
@AllArgsConstructor // Constructor con todos los argumentos
@Builder 
public class PacienteRequest {
	 @NotBlank(message = "El DNI no puede estar vacío.")
	    @Size(min = 8, max = 10, message = "El DNI debe tener entre 8 y 10 caracteres.")
	    @Pattern(regexp = "^[0-9]+$", message = "El DNI debe contener solo números.")
	    private String dni;

	    @NotBlank(message = "Los nombres no pueden estar vacíos.")
	    @Size(max = 100, message = "Los nombres no pueden exceder los 100 caracteres.")
	    private String nombres;

	    @NotBlank(message = "Los apellidos no pueden estar vacíos.")
	    @Size(max = 100, message = "Los apellidos no pueden exceder los 100 caracteres.")
	    private String apellidos;

	    @Past(message = "La fecha de nacimiento debe ser en el pasado.")
	    private LocalDate fechaNacimiento;

	    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres.")
	    @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números.")
	    private String telefono;

	    @Email(message = "El formato del correo electrónico no es válido.")
	    @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres.")
	    private String email;

	    // --- CONSTRUCTORES ---
	    // Constructor sin argumentos (necesario para la deserialización JSON)
	    public PacienteRequest() {
	    }

	    // Constructor con todos los argumentos (útil para el Builder y para creación directa)
	    private PacienteRequest(String dni, String nombres, String apellidos,
	                            LocalDate fechaNacimiento, String telefono, String email) {
	        this.dni = dni;
	        this.nombres = nombres;
	        this.apellidos = apellidos;
	        this.fechaNacimiento = fechaNacimiento;
	        this.telefono = telefono;
	        this.email = email;
	    }

	    // --- MÉTODO ESTATICO BUILDER ---
	    public static Builder builder() {
	        return new Builder();
	    }

	    // --- CLASE ANIDADA BUILDER ---
	    public static class Builder {
	        private String dni;
	        private String nombres;
	        private String apellidos;
	        private LocalDate fechaNacimiento;
	        private String telefono;
	        private String email;

	        public Builder dni(String dni) {
	            this.dni = dni;
	            return this;
	        }

	        public Builder nombres(String nombres) {
	            this.nombres = nombres;
	            return this;
	        }

	        public Builder apellidos(String apellidos) {
	            this.apellidos = apellidos;
	            return this;
	        }

	        public Builder fechaNacimiento(LocalDate fechaNacimiento) {
	            this.fechaNacimiento = fechaNacimiento;
	            return this;
	        }

	        public Builder telefono(String telefono) {
	            this.telefono = telefono;
	            return this;
	        }

	        public Builder email(String email) {
	            this.email = email;
	            return this;
	        }

	        public PacienteRequest build() {
	            return new PacienteRequest(dni, nombres, apellidos, fechaNacimiento, telefono, email);
	        }
	    }

	    // --- GETTERS ---
	    public String getDni() {
	        return dni;
	    }

	    public String getNombres() {
	        return nombres;
	    }

	    public String getApellidos() {
	        return apellidos;
	    }

	    public LocalDate getFechaNacimiento() {
	        return fechaNacimiento;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public String getEmail() {
	        return email;
	    }

	    // --- SETTERS (Opcionales para DTOs de Request, pero si los necesitas, inclúyelos) ---
	    // Generalmente, los DTOs Request son inmutables después de la construcción inicial.
	    public void setDni(String dni) { this.dni = dni; }
	    public void setNombres(String nombres) { this.nombres = nombres; }
	    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
	    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
	    public void setTelefono(String telefono) { this.telefono = telefono; }
	    public void setEmail(String email) { this.email = email; }

	    // --- toString(), equals(), hashCode() (Implementar manualmente si se necesitan) ---
	    @Override
	    public String toString() {
	        return "PacienteRequest{" +
	               "dni='" + dni + '\'' +
	               ", nombres='" + nombres + '\'' +
	               ", apellidos='" + apellidos + '\'' +
	               ", fechaNacimiento=" + fechaNacimiento +
	               ", telefono='" + telefono + '\'' +
	               ", email='" + email + '\'' +
	               '}';
	    }
	    // @Override public boolean equals(Object o) { ... }
	    // @Override public int hashCode() { ... }
    
    
}
