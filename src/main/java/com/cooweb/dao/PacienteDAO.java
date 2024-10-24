package com.cooweb.dao;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cooweb.models.Paciente;

import com.cooweb.models.Turno;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Transactional
public interface PacienteDAO {
    
    // Metodos funcionales y utilizados
    Paciente iniciarSesion(String email, String password);
	List<Paciente> getPacientes();
    Paciente actualizarInformacionContacto(int id_paciente, String nombre, String apellido, String dni, String email, String telefono, String password, String direccion);
    void eliminar(int id);
    void registrar(Paciente paciente);
    List<Paciente> getCorreos(String email);
    Paciente findByEmail(String email);
    String hashFunction(String password);
    boolean emailYaRegistrado(String email);
    boolean dniYaRegistrado(String dni);
    void cerrarSesion(HttpServletRequest request, HttpServletResponse response);
    Set<Turno> consultarTurnos(int id_paciente);

    // Metodos que debe realizar la parte de turnos (Damara)
    Turno agendarTurno(Paciente paciente, Date fecha_turno, Time hora_turno);
    void cancelarTurno();
    void consultarTurno();
    void suscribirseNotificaciones();
    void consultarHistorial();
    
    
    
    

}
