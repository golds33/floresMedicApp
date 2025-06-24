package com.tarea.floresMedicApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoResponse {
	private Long id;
    private String nombres;
    private String apellidos;
    private String especialidad;
    private String numeroColegiatura;
    private String telefono;
    private String email;

    // Constructores
    public MedicoResponse() {}
    private MedicoResponse(Long id, String nombres, String apellidos, String especialidad,
                           String numeroColegiatura, String telefono, String email) {
        this.id = id;
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
        private Long id;
        private String nombres;
        private String apellidos;
        private String especialidad;
        private String numeroColegiatura;
        private String telefono;
        private String email;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder nombres(String nombres) { this.nombres = nombres; return this; }
        public Builder apellidos(String apellidos) { this.apellidos = apellidos; return this; }
        public Builder especialidad(String especialidad) { this.especialidad = especialidad; return this; }
        public Builder numeroColegiatura(String numeroColegiatura) { this.numeroColegiatura = numeroColegiatura; return this; }
        public Builder telefono(String telefono) { this.telefono = telefono; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public MedicoResponse build() {
            return new MedicoResponse(id, nombres, apellidos, especialidad, numeroColegiatura, telefono, email);
        }
    }

    // Getters
    public Long getId() { return id; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getEspecialidad() { return especialidad; }
    public String getNumeroColegiatura() { return numeroColegiatura; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    // toString() (Opcional)
    @Override
    public String toString() {
        return "MedicoResponse{" +
               "id=" + id +
               ", nombres='" + nombres + '\'' +
               ", apellidos='" + apellidos + '\'' +
               ", especialidad='" + especialidad + '\'' +
               ", numeroColegiatura='" + numeroColegiatura + '\'' +
               ", telefono='" + telefono + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
