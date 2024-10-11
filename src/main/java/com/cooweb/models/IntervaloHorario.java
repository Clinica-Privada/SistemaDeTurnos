package com.cooweb.models;

import java.sql.Time;

public class IntervaloHorario {
	private int id;
	private Time horaInicio;
	private Time horaFin;
	private enum dia {
		Lunes, Martes, Miercoles, Jueves, Sabado, Domingo
	}

	private int idProfesional;

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

	public int getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(int idProfesional) {
		this.idProfesional = idProfesional;
	}
	
	
}
