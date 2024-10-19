package com.cooweb.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import com.cooweb.models.Profesional;

@Entity
@Table(name = "especialidad") 
public class Especialidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    @Column(name="id_especialidad")
    private int id_especialidad;
    @Column(name="nombre_especialidad")
    private String nombre_especialidad;
    @Column(name="descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "especialidad")
    private List<Profesional> profesionales; // Lista de profesionales relacionados con esta especialidad

    
    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdminEspecialidad> admin_especialidades;

    public Especialidad() {}

    public Especialidad(int id_especialidad, String nombre_especialidad, String descripcion) {
        this.id_especialidad = id_especialidad;
        this.nombre_especialidad = nombre_especialidad;
        this.descripcion = descripcion;
    }

    



    public Especialidad(int id_especialidad, String nombre_especialidad, String descripcion,
            List<Profesional> profesionales, Set<AdminEspecialidad> admin_especialidades) {
        this.id_especialidad = id_especialidad;
        this.nombre_especialidad = nombre_especialidad;
        this.descripcion = descripcion;
        this.profesionales = profesionales;
        this.admin_especialidades = admin_especialidades;
    }

    // Getters y Setters
    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int idEspecialidad) {
        this.id_especialidad = idEspecialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre_especialidad() {
        return nombre_especialidad;
    }

    public void setNombre_especialidad(String nombre_especialidad) {
        this.nombre_especialidad = nombre_especialidad;
    }

    public List<Profesional> getProfesionales() {
        return profesionales;
    }

    public void setProfesionales(List<Profesional> profesionales) {
        this.profesionales = profesionales;
    }

    public Set<AdminEspecialidad> getAdmin_especialidades() {
        return admin_especialidades;
    }

    public void setAdmin_especialidades(Set<AdminEspecialidad> admin_especialidades) {
        this.admin_especialidades = admin_especialidades;
    }


  
}
