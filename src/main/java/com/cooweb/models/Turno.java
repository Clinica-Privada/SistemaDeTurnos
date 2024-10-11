package com.cooweb.models;

import java.sql.Time;
import java.util.Date;

public class Turno {
	
	private int idTurno;
	private Date fechaTurno;
	private Time horaTurno;
	private int idPaciente;
	private int idProfesional;
	private int idServicio;
	private int idAdmin;
	
	private enum estadoTurno {
		pendiente, confirmado, cancelado, completado
		
	}
	

	public Turno () {}
	
	public Turno(int idTurno, Date fechaTurno, Time horaTurno, int idPaciente, int idProfesional, int idServicio,
			int idAdmin) {
		super();
		this.idTurno = idTurno;
		this.fechaTurno = fechaTurno;
		this.horaTurno = horaTurno;
		this.idPaciente = idPaciente;
		this.idProfesional = idProfesional;
		this.idServicio = idServicio;
		this.idAdmin = idAdmin;
	}

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

	public void setHoraturno(Time horaturno) {
		this.horaTurno = horaturno;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public int getIdProfesional() {
		return idProfesional;
	}

	public void setIdProfesional(int idProfesional) {
		this.idProfesional = idProfesional;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}



	public String getfechaTurno() {
		// TODO Auto-generated method stub
		return null;
	}
}
