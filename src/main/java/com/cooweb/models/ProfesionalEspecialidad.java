package com.cooweb.models;

import jakarta.persistence.*;

@Entity
@Table(name = "profesionalespecialidad") 
public class ProfesionalEspecialidad {
    
    @EmbeddedId
    private ProfesionalEspecialidadId id;

    @ManyToOne
    @MapsId("idProfesional") // Mapea el ID del Profesional de la clave compuesta
    @JoinColumn(name = "idProfesional")
    private Profesional profesional;

    @ManyToOne
    @MapsId("idEspecialidad") // Mapea el ID de la Especialidad de la clave compuesta
    @JoinColumn(name = "idEspecialidad")
    private Especialidad especialidad;

    // Constructor por defecto
    public ProfesionalEspecialidad() {}

    // Getters y setters
    public ProfesionalEspecialidadId getId() {
        return id;
    }

    public void setId(ProfesionalEspecialidadId id) {
        this.id = id;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
