package com.cooweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int numeroEmpleado;
	//constructores
	
	 public Admin() {}
	 
	//hasta aca atributos
	
	//empiezan los getters y setters...
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroEmpleado() {
		return numeroEmpleado;
	}
	public void setNumeroEmpleado(int numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}
	
	//empiazan los metodos
	
	public void gestionarUsuario() {
        // Aquí iría la lógica para crear, editar o eliminar usuarios
        System.out.println("Gestionando usuario.");
    }
	
	 public void gestionarServicio() {
	        // Aquí se definiría la lógica para crear, editar o eliminar servicios
	        System.out.println("Gestionando servicio.");
	    }
	 
	 public void definirHorarioAtencion() {
	        // Aquí se definirían los horarios del profesional
	        System.out.println("Definiendo horario de atención.");
	    }
	 
	 public void gestionarEspecialidad() {
	        // Aquí se podría agregar o eliminar una especialidad
	        System.out.println("Gestionando especialidad.");
	    }
	 
	 public void gestionarTurno() {
	        // Aquí iría la lógica para gestionar turnos
	        System.out.println("Gestionando turno.");
	    }
	 
	 public void recibirReporte() {
	        // Aquí iría la lógica para recibir y procesar reportes
	        System.out.println("Recibiendo reporte.");
	    }
	
	

}
