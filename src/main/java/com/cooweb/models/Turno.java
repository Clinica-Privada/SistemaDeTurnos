package com.cooweb.models;

import java.sql.Time;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "turno") 
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_turno")
    private int id_turno;

    @Column(name="fecha_turno")
    private Date fecha_turno;

    @Column(name="hora_turno")
    private Time hora_turno;

    @ManyToOne // Relación con Paciente
    @JoinColumn(name = "id_paciente", nullable = false) // Relación obligatoria
    private Paciente paciente; 

    @ManyToOne // Relación con Profesional
    @JoinColumn(name = "id_profesional", nullable = false) // Relación obligatoria
    private Profesional profesional;

    @ManyToOne // Relación con Especialidad
    @JoinColumn(name = "id_especialidad", nullable = false) // Relación obligatoria
    private Especialidad especialidad; // Cambiado de idServicio a especialidad

    @ManyToOne // Relación con Admin
    @JoinColumn(name = "id_admin", nullable = false) // Relación obligatoria
    private Admin admin;

    @Enumerated(EnumType.STRING) 
    @Column(name="estado_turno")
    private EstadoTurno estado_turno;

    // Enum de estados del turno
    public enum EstadoTurno {
        PENDIENTE, CONFIRMADO, CANCELADO, COMPLETADO
    }

    public Turno() {}

   

    public Turno(int id_turno, Date fecha_turno, Time hora_turno, Paciente paciente, Profesional profesional,
            Especialidad especialidad, Admin admin, EstadoTurno estado_turno) {
        this.id_turno = id_turno;
        this.fecha_turno = fecha_turno;
        this.hora_turno = hora_turno;
        this.paciente = paciente;
        this.profesional = profesional;
        this.especialidad = especialidad;
        this.admin = admin;
        this.estado_turno = estado_turno;
    }



    // Getters y Setters
   

    public Paciente getPaciente() {
        return paciente;
    }

    public int getId_turno() {
        return id_turno;
    }



    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }



    public Date getFecha_turno() {
        return fecha_turno;
    }



    public void setFecha_turno(Date fecha_turno) {
        this.fecha_turno = fecha_turno;
    }



    public Time getHora_turno() {
        return hora_turno;
    }



    public void setHora_turno(Time hora_turno) {
        this.hora_turno = hora_turno;
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

    public EstadoTurno getEstado_turno() {
        return estado_turno;
    }

    public void setEstado_turno(EstadoTurno estado_turno) {
        this.estado_turno = estado_turno;
    }
}
