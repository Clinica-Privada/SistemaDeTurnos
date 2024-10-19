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
    @Column(name="idEspecialidad")
    private int idEspecialidad;
    @Column(name="nombreEspecialidad")
    private String nombreEspecialidad;
    @Column(name="descripcion")
    private String descripcion;
    
    @OneToMany(mappedBy = "especialidad")
    private List<Profesional> profesionales; // Lista de profesionales relacionados con esta especialidad

    
    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdminEspecialidad> adminEspecialidades;

    public Especialidad() {}

    public Especialidad(int idEspecialidad, String nombreEspecialidad, String descripcion) {
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
        this.descripcion = descripcion;
    }

    



    public Especialidad(int idEspecialidad, String nombreEspecialidad, String descripcion,
            List<Profesional> profesionales, Set<AdminEspecialidad> adminEspecialidades) {
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
        this.descripcion = descripcion;
        this.profesionales = profesionales;
        this.adminEspecialidades = adminEspecialidades;
    }

    // Getters y Setters
    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Set<AdminEspecialidad> getAdminEspecialidades() {
        return adminEspecialidades;
    }

    public void setAdminEspecialidades(Set<AdminEspecialidad> adminEspecialidades) {
        this.adminEspecialidades = adminEspecialidades;
    }
}
