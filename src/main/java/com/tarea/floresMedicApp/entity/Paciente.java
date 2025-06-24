package com.tarea.floresMedicApp.entity;

import java.time.LocalDate;

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

@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
@Builder // Provides a builder pattern for object creation
@Entity // Marks this class as a JPA entity
@Table(name = "pacientes") 
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String dni;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(nullable = true)
    private LocalDate fechaNacimiento;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    // --- CONSTRUCTORES ---
    // ¡OBLIGATORIO para JPA! Constructor sin argumentos
    public Paciente() {
    }

    // Constructor privado para el Builder (si la entidad se crea con Builder)
    private Paciente(Long id, String dni, String nombres, String apellidos,
                     LocalDate fechaNacimiento, String telefono, String email) {
        this.id = id;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
    }

    // Constructor con campos obligatorios/iniciales (opcional, si se crea sin Builder a veces)
    public Paciente(String dni, String nombres, String apellidos, LocalDate fechaNacimiento, String telefono, String email) {
        this(null, dni, nombres, apellidos, fechaNacimiento, telefono, email); // Llama al constructor privado
    }

    // --- MÉTODO ESTATICO BUILDER ---
    public static Builder builder() {
        return new Builder();
    }

    // --- CLASE ANIDADA BUILDER ---
    public static class Builder {
        private Long id;
        private String dni;
        private String nombres;
        private String apellidos;
        private LocalDate fechaNacimiento;
        private String telefono;
        private String email;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder dni(String dni) { this.dni = dni; return this; }
        public Builder nombres(String nombres) { this.nombres = nombres; return this; }
        public Builder apellidos(String apellidos) { this.apellidos = apellidos; return this; }
        public Builder fechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; return this; }
        public Builder telefono(String telefono) { this.telefono = telefono; return this; }
        public Builder email(String email) { this.email = email; return this; }

        public Paciente build() {
            return new Paciente(id, dni, nombres, apellidos, fechaNacimiento, telefono, email);
        }
    }

    // --- GETTERS (Necesarios para leer datos de la entidad) ---
    public Long getId() { return id; }
    public String getDni() { return dni; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    // --- SETTERS (Necesarios para actualizar la entidad antes de save()) ---
    public void setId(Long id) { this.id = id; }
    public void setDni(String dni) { this.dni = dni; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }

    // --- toString(), equals(), hashCode() (Implementar manualmente si se necesitan) ---
    @Override
    public String toString() {
        return "Paciente{" +
               "id=" + id +
               ", dni='" + dni + '\'' +
               ", nombres='" + nombres + '\'' +
               ", apellidos='" + apellidos + '\'' +
               ", fechaNacimiento=" + fechaNacimiento +
               ", telefono='" + telefono + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
