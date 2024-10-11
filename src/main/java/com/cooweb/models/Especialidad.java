package com.cooweb.models;

import jakarta.persistence.*;
import java.util.Set;

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
    
    @ManyToOne
    @JoinColumn(name = "idProfesional", referencedColumnName = "idProfesional", nullable = false)
    private Profesional profesional; // Relación con Profesional


    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdminEspecialidad> adminEspecialidades;

    public Especialidad() {}

    public Especialidad(int id, String nombreEspecialidad, String descripcion, int idServicio) {
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
        this.descripcion = descripcion;
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
