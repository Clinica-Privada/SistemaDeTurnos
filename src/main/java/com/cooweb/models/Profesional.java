package com.cooweb.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "profesional") 
public class Profesional extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProfesional")
    private int idProfesional;
    @Column(name="matricula")
    private int matricula;

    @ManyToOne
    @JoinColumn(name = "idEspecialidad") // La FK hacia Especialidad
    private Especialidad especialidad;
    
    @OneToMany(mappedBy = "profesional", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IntervaloHorario> intervalos;

    // Getters y Setters
    public int getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
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

    // Constructor con parámetros (opcional)
    public Profesional(String nombre, String apellido, String email, String telefono, String password,
                       Date fechaNacimiento, String direccion, int matricula) {
        super(nombre, apellido, email, telefono, password, fechaNacimiento, direccion);
        this.matricula = matricula;
    }
}
