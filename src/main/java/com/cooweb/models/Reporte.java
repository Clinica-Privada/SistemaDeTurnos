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

    @Column(name = "fechaReporte")
    private LocalDate fechaReporte;

    @ManyToOne
    @JoinColumn(name = "idProfesional")
    private Profesional idProfesional; // Relación con Profesional

    @ManyToOne
    @JoinColumn(name = "idAdmin") // Relación con Admin
    private Admin idAdmin; // Relación con Admin

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
        return fechaReporte;
    }

    public void setFecha(LocalDate fecha) {
        this.fechaReporte = fecha;
    }

    public Profesional getProfesional() {
        return idProfesional;
    }

    public void setProfesional(Profesional idProfesional) {
        this.idProfesional = idProfesional;
    }

    public Admin getAdmin() {
        return idAdmin;
    }

    public void setAdmin(Admin idAdmin) {
        this.idAdmin = idAdmin;
    }

    // Constructor por defecto
    public Reporte() {
    }

    // Constructor con parámetros
    public Reporte(String contenido, LocalDate fechaReporte, Profesional idProfesional, Admin idAdmin) {
        this.contenido = contenido;
        this.fechaReporte = fechaReporte;
        this.idProfesional = idProfesional;
        this.idAdmin = idAdmin;
    }
}
