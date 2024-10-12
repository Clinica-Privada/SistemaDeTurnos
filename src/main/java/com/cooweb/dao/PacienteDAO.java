package com.cooweb.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
    void actualizarInformacionContacto();
    
    List<Paciente> getPacientes();
    void eliminar(Long id);
    void registrar(Paciente paciente);

}
