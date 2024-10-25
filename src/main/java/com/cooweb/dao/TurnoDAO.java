package com.cooweb.dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cooweb.models.Admin;
import com.cooweb.models.Especialidad;
import com.cooweb.models.Paciente;
import com.cooweb.models.Profesional;
import com.cooweb.models.Turno;

@Repository
public interface TurnoDAO {
	void crearTurno(Turno turno);
    void confirmarAsistencia(int idTurno);
    void reprogramarTurno(int idTurno, Turno turnoActualizado);
    Turno obtenerTurnoPorId(int idTurno);
    List<Turno> obtenerTodosLosTurnos();
    void actualizarTurno(Turno turno);
    
    
    void gestionarTurno (Admin admin);
    
    void consultarTurno (Profesional profesional);






void consultarTurno(Paciente paciente);
void suscribirseNotificaciones(Paciente paciente);
void consultarHistorial(Paciente paciente);
void cancelarTurno(int idTurno);
Turno agendarTurno(Paciente paciente, Date fecha_turno, Time hora_turno, Profesional profesional,
		Especialidad especialidad); 

}