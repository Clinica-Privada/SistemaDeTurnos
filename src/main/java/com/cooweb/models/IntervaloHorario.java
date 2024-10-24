package com.cooweb.models;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "intervalohorario") 
public class IntervaloHorario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    @Column(name="idIntervaloHorario")
    private int id;
    @Column(name="horaInicio")
    private Time horaInicio;
    @Column(name="horaFin")
    private Time horaFin;
    @Column(name="dia")
    @Enumerated(EnumType.STRING) // Guarda el nombre del enum como String en la base de datos
    private Dia dia;

    @ManyToOne // Relación muchos a uno con Profesional
    @JoinColumn(name = "idProfesional", nullable = false) // Cambia el nombre de la columna según tu esquema de base de datos
    private Profesional profesional;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public enum Dia {
        Lunes, Martes, Miercoles, Jueves, Sabado, Domingo
    }
}
