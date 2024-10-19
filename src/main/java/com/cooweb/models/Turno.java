package com.cooweb.models;

import java.sql.Time;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "turno") 
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idTurno")
    private int idTurno;

    @Column(name="fechaTurno")
    private Date fechaTurno;

    @Column(name="horaTurno")
    private Time horaTurno;

    @ManyToOne // Relación con Paciente
    @JoinColumn(name = "idPaciente", nullable = false) // Relación obligatoria
    private Paciente paciente; 

    @ManyToOne // Relación con Profesional
    @JoinColumn(name = "idProfesional", nullable = false) // Relación obligatoria
    private Profesional profesional;

    @ManyToOne // Relación con Especialidad
    @JoinColumn(name = "idEspecialidad", nullable = false) // Relación obligatoria
    private Especialidad especialidad; // Cambiado de idServicio a especialidad

    @ManyToOne // Relación con Admin
    @JoinColumn(name = "idAdmin", nullable = false) // Relación obligatoria
    private Admin admin;

    @Enumerated(EnumType.STRING) 
    @Column(name="estado")
    private EstadoTurno estadoTurno;

    // Enum de estados del turno
    public enum EstadoTurno {
        PENDIENTE, CONFIRMADO, CANCELADO, COMPLETADO
    }

    public Turno() {}

    public Turno(Date fechaTurno, Time horaTurno, Paciente paciente, Profesional profesional, Especialidad especialidad, Admin admin, EstadoTurno estadoTurno) {
        this.fechaTurno = fechaTurno;
        this.horaTurno = horaTurno;
        this.paciente = paciente;
        this.profesional = profesional;
        this.especialidad = especialidad; // Inicializa la especialidad
        this.admin = admin; // Inicializa el admin
        this.estadoTurno = estadoTurno; // Inicialización del estado
    }

    // Getters y Setters
    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public Date getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(Date fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public Time getHoraTurno() {
        return horaTurno;
    }

    public void setHoraTurno(Time horaTurno) {
        this.horaTurno = horaTurno;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public EstadoTurno getEstadoTurno() {
        return estadoTurno;
    }

    public void setEstadoTurno(EstadoTurno estadoTurno) {
        this.estadoTurno = estadoTurno;
    }
}
