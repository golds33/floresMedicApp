package com.tarea.floresMedicApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoRequest {
	@NotBlank(message = "Los nombres no pueden estar vacíos.")
    @Size(max = 100, message = "Los nombres no pueden exceder los 100 caracteres.")
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos.")
    @Size(max = 100, message = "Los apellidos no pueden exceder los 100 caracteres.")
    private String apellidos;

    @NotBlank(message = "La especialidad no puede estar vacía.")
    @Size(max = 100, message = "La especialidad no puede exceder los 100 caracteres.")
    private String especialidad;

    @NotBlank(message = "El número de colegiatura no puede estar vacío.")
    @Size(max = 20, message = "El número de colegiatura no puede exceder los 20 caracteres.")
    @Pattern(regexp = "^[0-9]+$", message = "El número de colegiatura debe contener solo números.")
    private String numeroColegiatura;

    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres.")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números.")
    private String telefono;

    @Email(message = "El formato del correo electrónico no es válido.")
    @Size(max = 100, message = "El correo electrónico no puede exceder los 100 caracteres.")
    private String email;

    // Constructores
    public MedicoRequest() {}
    private MedicoRequest(String nombres, String apellidos, String especialidad,
                          String numeroColegiatura, String telefono, String email) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.numeroColegiatura = numeroColegiatura;
        this.telefono = telefono;
        this.email = email;
    }

    // Builder
    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private String nombres;
        private String apellidos;
        private String especialidad;
        private String numeroColegiatura;
        private String telefono;
        private String email;

        public Builder nombres(String nombres) { this.nombres = nombres; return this; }
        public Builder apellidos(String apellidos) { this.apellidos = apellidos; return this; }
        public Builder especialidad(String especialidad) { this.especialidad = especialidad; return this; }
        public Builder numeroColegiatura(String numeroColegiatura) { this.numeroColegiatura = numeroColegiatura; return this; }
        public Builder telefono(String telefono) { this.telefono = telefono; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public MedicoRequest build() {
            return new MedicoRequest(nombres, apellidos, especialidad, numeroColegiatura, telefono, email);
        }
    }

    // Getters
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getEspecialidad() { return especialidad; }
    public String getNumeroColegiatura() { return numeroColegiatura; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    // Setters (Opcional)
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setNumeroColegiatura(String numeroColegiatura) { this.numeroColegiatura = numeroColegiatura; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }

    // toString() (Opcional)
    @Override
    public String toString() {
        return "MedicoRequest{" +
               "nombres='" + nombres + '\'' +
               ", apellidos='" + apellidos + '\'' +
               ", especialidad='" + especialidad + '\'' +
               ", numeroColegiatura='" + numeroColegiatura + '\'' +
               ", telefono='" + telefono + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
    
}
