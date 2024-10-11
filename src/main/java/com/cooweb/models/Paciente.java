package com.cooweb.models;

import jakarta.persistence.*;
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
    @Column(name="numero_historia_clinica")
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
    
    public Paciente(String nombre, String apellido, String email, String telefono, String password,
   Date fechaNacimiento, String direccion, int numeroHistoriaClinica) {
   super(nombre, apellido, email, telefono, password, fechaNacimiento, direccion);
   this.numeroHistoriaClinica = numeroHistoriaClinica;

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
}
