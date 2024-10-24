package com.cooweb.models;

import java.util.Date;
import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "profesional") 
public class Profesional extends Usuario {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_profesional")
    private int id_profesional;
    @Column(name="matricula", nullable = false)
    private String matricula;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_especialidad", referencedColumnName ="id_especialidad") // La FK hacia Especialidad
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Set<IntervaloHorario> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(Set<IntervaloHorario> intervalos) {
        this.intervalos = intervalos;
    }

    public int getIdespecialidad() {
        return especialidad.getId_especialidad(); // Obtener el id de la especialidad
    }

    // Constructor por defecto
    public Profesional() {

    }

    // Constructor que recibe id_especialidad
    public Profesional(String nombre, String apellido, String dni, String email, String telefono, String password,
                       Date fecha_nacimiento, String direccion, Especialidad especialidad) {
        super(nombre, apellido, dni, email, telefono, password, fecha_nacimiento, direccion);
        this.matricula = dni;
        this.especialidad = especialidad; // Se asigna el objeto Especialidad

    }

    // Constructor
    
    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    

    
    
}
