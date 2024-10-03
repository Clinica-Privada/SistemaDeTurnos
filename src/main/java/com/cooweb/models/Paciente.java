package com.cooweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Paciente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int numeroHistoriaClinica;
	
	//atributos
	//constructor
	
	public Paciente() {}
	
	//getters setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroHistoriaClinica() {
		return numeroHistoriaClinica;
	}
	public void setNumeroHistoriaClinica(int numeroHistoriaClinica) {
		this.numeroHistoriaClinica = numeroHistoriaClinica;
	}
	 
	//metodos

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
