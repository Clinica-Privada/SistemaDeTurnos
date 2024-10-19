package com.cooweb.dao;
import java.util.List;

import com.cooweb.models.Paciente;

import com.cooweb.models.Turno;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Transactional
public interface PacienteDAO {
    Paciente iniciarSesion(String email, String password);
	void cerrarSesion(HttpServletRequest request, HttpServletResponse response);
    Turno agendarTurno();
    void cancelarTurno();
    void consultarTurno();
    void suscribirseNotificaciones();
    void consultarHistorial();
    Paciente actualizarInformacionContacto(int idPaciente, String nombre, String apellido, String dni, String email, String telefono, String password, String direccion);
    Paciente findByEmail(String email);
    List<Paciente> getPacientes();
    List<Paciente> getCorreos(String email);
    void eliminar(int id);
    void registrar(Paciente paciente);

}
