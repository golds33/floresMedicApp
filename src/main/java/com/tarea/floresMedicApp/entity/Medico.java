package com.tarea.floresMedicApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "medicos")
public class Medico {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = false, length = 100)
    private String especialidad;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroColegiatura; // Número de colegiatura del médico

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    // Constructores
    public Medico() {}
    private Medico(Long id, String nombres, String apellidos, String especialidad,
                   String numeroColegiatura, String telefono, String email) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.numeroColegiatura = numeroColegiatura;
        this.telefono = telefono;
        this.email = email;
    }

    public Medico(String nombres, String apellidos, String especialidad, String numeroColegiatura, String telefono, String email) {
        this(null, nombres, apellidos, especialidad, numeroColegiatura, telefono, email);
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
        public Medico build() {
            return new Medico(id, nombres, apellidos, especialidad, numeroColegiatura, telefono, email);
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

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setNumeroColegiatura(String numeroColegiatura) { this.numeroColegiatura = numeroColegiatura; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }

    // toString() (Opcional)
    @Override
    public String toString() {
        return "Medico{" +
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
