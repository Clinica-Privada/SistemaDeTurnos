package com.cooweb.dao;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Repository;

import com.cooweb.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import com.cooweb.models.Paciente;
import com.cooweb.models.Turno;

@Repository
@Transactional
public class PacienteDAOImp implements PacienteDAO{
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public Paciente iniciarSesion(String email, String password) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iniciarSesion'");
	}

	@Override
	public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cerrarSesion'");
	}

	@Override
	public Turno agendarTurno() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'agendarTurno'");
	}

	@Override
	public void cancelarTurno() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cancelarTurno'");
	}

	@Override
	public void consultarTurno() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'consultarTurno'");
	}

	@Override
	public void suscribirseNotificaciones() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'suscribirseNotificaciones'");
	}

	@Override
	public void consultarHistorial() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'consultarHistorial'");
	}

	@Override
	public void actualizarInformacionContacto() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actualizarInformacionContacto'");
	}

	@Override
	public List<Paciente> listarTodos() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
	}

	@Override
	public Paciente leerPorId(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'leerPorId'");
	}

	@Override
	public void registrar(Paciente t) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'registrar'");
	}

	@Override
	public void actualizar(Paciente t) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
	}
	
	
}
