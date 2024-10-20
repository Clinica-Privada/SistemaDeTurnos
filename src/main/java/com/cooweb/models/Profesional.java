package com.cooweb.models;

import java.util.Date;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "profesional") 
public class Profesional extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_profesional")
    private int id_profesional;
    @Column(name="matricula", nullable = false)
    private int matricula;
    
    @ManyToOne
    @JoinColumn(name = "id_especialidad", referencedColumnName ="id_especialidad", nullable = false ) // La FK hacia Especialidad
    private Especialidad especialidad;
    
    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IntervaloHorario> intervalos;

    // Getters y Setters
    public int getId_profesional() {
        return id_profesional;
    }

    public void setId_profesional(int id_profesional) {
        this.id_profesional = id_profesional;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Set<IntervaloHorario> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(Set<IntervaloHorario> intervalos) {
        this.intervalos = intervalos;
    }

    // Constructor por defecto
    public Profesional() {
    }

    public Profesional(String nombre, String apellido, String dni, String email, String telefono, String password,
            Date fechaNacimiento, String direccion, int id_profesional, int matricula, Especialidad especialidad,
            Set<IntervaloHorario> intervalos) {
        super(nombre, apellido, dni, email, telefono, password, fechaNacimiento, direccion);
        this.id_profesional = id_profesional;
        this.matricula = matricula;
        this.especialidad = especialidad;
        this.intervalos = intervalos;
    }

    // Constructor con parámetros (opcional)
    
}
