package com.cooweb.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "paciente") 
public class Paciente extends Usuario {
    
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paciente")
    private int idPaciente;

    private LocalDateTime fechaRegistro;

    @Column(name="numero_historia_clinica", nullable = false)
    private int numeroHistoriaClinica;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Turno> turnos;

    // Getters y Setters
    public int getIdPaciente() {
        return idPaciente;
    }
    
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }
    
    public int getNumeroHistoriaClinica() {
        return numeroHistoriaClinica;
    }
    
    public void setNumeroHistoriaClinica(int numeroHistoriaClinica) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }
    
    public Set<Turno> getTurnos() {
        return turnos;
    }
    
    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }
    
    // Constructores 
    public Paciente() {
    }
    
    

    public Paciente(String nombre, String apellido, String dni, String email, String telefono, String password,
            String fechaNacimiento, String direccion, int idPaciente, int numeroHistoriaClinica, Set<Turno> turnos) {
        super(nombre, apellido, dni, email, telefono, password, fechaNacimiento, direccion);
        this.idPaciente = idPaciente;
        this.numeroHistoriaClinica = numeroHistoriaClinica;
        this.turnos = turnos;
    }

    // Métodos
    public void agendarTurno() {
        System.out.println("Agendando turno.");
    }
    
    public void cancelarTurno() {
        System.out.println("Cancelando turno.");
    }

    public void consultarTurno() {
        System.out.println("Consultando turno.");
    }

    public void suscribirseNotificaciones() {
        System.out.println("Suscribiéndose a notificaciones.");
    }

    public void consultarHistorial() {
        System.out.println("Consultando historial médico.");
    }

    public void actualizarInformacionContacto() {
        System.out.println("Actualizando información de contacto.");
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
