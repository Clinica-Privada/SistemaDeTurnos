package com.cooweb.models;

import java.util.List;

public class Especialidad {
	
	private int id;
	private String nombreEspecialidad;
	private String descripcion;
	private int idServicio;
	
	public Especialidad () {}
	
	
	public Especialidad(int id, String nombreEspecialidad, String descripcion, int idServicio) {
		super();
		this.id = id;
		this.nombreEspecialidad = nombreEspecialidad;
		this.descripcion = descripcion;
		this.idServicio = idServicio;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getIdServicio() {
		return idServicio;
	}
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	
}

    

