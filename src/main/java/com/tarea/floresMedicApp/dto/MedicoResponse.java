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
    private String dni; // Asumo que el DNI también se retornaría en la respuesta
    private String nombres;
    private String apellidos;
    private String especialidad;
    private String numeroColegiatura;
    private String telefono;
    private String email;

    // --- CONSTRUCTORES ---

    // Constructor vacío (NoArgsConstructor equivalente de Lombok)
    public MedicoResponse() {
        super();
    }

    // Constructor con todos los argumentos (AllArgsConstructor equivalente de Lombok)
    public MedicoResponse(Long id, String dni, String nombres, String apellidos,
                          String especialidad, String numeroColegiatura,
                          String telefono, String email) {
        super();
        this.id = id;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.numeroColegiatura = numeroColegiatura;
        this.telefono = telefono;
        this.email = email;
    }

    // --- GETTERS ---
    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getNumeroColegiatura() {
        return numeroColegiatura;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    // --- SETTERS ---
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

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setNumeroColegiatura(String numeroColegiatura) {
        this.numeroColegiatura = numeroColegiatura;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // --- MÉTODO toString() ---
    @Override
    public String toString() {
        return "MedicoResponse{" +
               "id=" + id +
               ", dni='" + dni + '\'' +
               ", nombres='" + nombres + '\'' +
               ", apellidos='" + apellidos + '\'' +
               ", especialidad='" + especialidad + '\'' +
               ", numeroColegiatura='" + numeroColegiatura + '\'' +
               ", telefono='" + telefono + '\'' +
               ", email='" + email + '\'' +
               '}';
    }

    // --- BUILDER (Implementación manual, equivalente a @Builder de Lombok) ---
    public static MedicoResponseBuilder builder() {
        return new MedicoResponseBuilder();
    }

    public static class MedicoResponseBuilder {
        private Long id;
        private String dni;
        private String nombres;
        private String apellidos;
        private String especialidad;
        private String numeroColegiatura;
        private String telefono;
        private String email;

        public MedicoResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MedicoResponseBuilder dni(String dni) {
            this.dni = dni;
            return this;
        }

        public MedicoResponseBuilder nombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public MedicoResponseBuilder apellidos(String apellidos) {
            this.apellidos = apellidos;
            return this;
        }

        public MedicoResponseBuilder especialidad(String especialidad) {
            this.especialidad = especialidad;
            return this;
        }

        public MedicoResponseBuilder numeroColegiatura(String numeroColegiatura) {
            this.numeroColegiatura = numeroColegiatura;
            return this;
        }

        public MedicoResponseBuilder telefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public MedicoResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public MedicoResponse build() {
            return new MedicoResponse(id, dni, nombres, apellidos, especialidad, numeroColegiatura, telefono, email);
        }
    }
    
}
