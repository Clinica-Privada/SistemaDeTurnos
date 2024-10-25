package com.cooweb.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "paciente") 
public class Paciente extends Usuario {
    
    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paciente")
    private int id_paciente;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fecha_registro;

    @Column(name="numero_historia_clinica")
    private String numero_historia_clinica;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Turno> turnos;

    // Getters y Setters
    
    // id paciente
    
    public int getId_paciente() {
        return id_paciente;
    }

    // fecha registro

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    // historia clinica 
    public String getNumero_historia_clinica() {
        return numero_historia_clinica;
    }

    public void setNumero_historia_clinica(String numero_historia_clinica) {
        this.numero_historia_clinica = numero_historia_clinica;
    }

    // turnos 
    public Set<Turno> getTurnos() {
        return turnos;
    }
    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }
  
    
    // Constructores 
    public Paciente() {

    }

    public Paciente(String nombre, String apellido, String dni, String email, String telefono, String password,
        Date fecha_nacimiento, String direccion) {
        super(nombre, apellido , dni, email, telefono, password, fecha_nacimiento, direccion);
        this.fecha_registro = LocalDateTime.now();  // Asegúrate de inicializar aquí
        this.numero_historia_clinica = dni;
    }

    
    
    

    
  
}
