package com.cooweb.models;

import java.time.LocalDate; 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "reporte") 
public class Reporte {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="idReporte")
    private int idReporte;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idProfesional")
    private Profesional profesional; // Relación con Profesional

    @ManyToOne
    @JoinColumn(name = "idAdmin") // Relación con Admin
    private Admin admin; // Relación con Admin

    // Getters y Setters
    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    // Constructor por defecto
    public Reporte() {
    }

    // Constructor con parámetros
    public Reporte(String contenido, LocalDate fecha, Profesional profesional, Admin admin) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.profesional = profesional;
        this.admin = admin;
    }
}
