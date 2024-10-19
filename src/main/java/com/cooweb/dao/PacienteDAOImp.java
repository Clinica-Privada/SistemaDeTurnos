package com.cooweb.dao;

import java.util.List;


import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
		// Consulta el paciente por email
		String query = "FROM Paciente WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes = entityManager.createQuery(query)
									.setParameter("email", email)
									.getResultList();
		// Verifica si se encontró algún paciente con ese email
		if (pacientes.isEmpty()) {
			throw new RuntimeException("Email no registrado.");
		}

		// Obtén el paciente encontrado
		Paciente paciente = pacientes.get(0);
	
		// Verifica que la contraseña coincida usando BCrypt
		if (password != paciente.getPassword()) {
			throw new RuntimeException("Contraseña incorrecta.");
		}
		
		// Si la autenticación es exitosa, retorna el paciente
		return paciente;
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


	@SuppressWarnings("unchecked")
	@Override
	@Transactional 
	public List<Paciente> getPacientes(){
		String query="from Paciente";
		List<Paciente> resultado = entityManager.createQuery(query).getResultList();
		return resultado;   
	}
	@Override
	
	public void eliminar(int id_paciente){
		Paciente paciente=entityManager.find(Paciente.class, id_paciente);
		entityManager.remove(paciente);
	}

	@Override
	public void registrar(Paciente t) {
		
		// Valida que el email no esté registrado
		String query ="FROM Paciente WHERE email= :email";
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes = entityManager.createQuery(query)
								.setParameter("email", t.getEmail())
								.getResultList();
		if(!pacientes.isEmpty()){
			throw new RuntimeException("El email ya está registrado.");
		}
		/* 
		// Valida que el DNI no esté ya registrado
		String queryDNI = "FROM Paciente WHERE dni = :dni";
		List<Usuario> usuariosPorDNI = entityManager.createQuery(queryDNI)
								.setParameter("dni", t.getId())
								.getResultList();
		
		if (!usuariosPorDNI.isEmpty()) {
			throw new RuntimeException("El DNI ya está registrado.");
		}
		*/
		//Hashear la contraseña antes de guardar
		
		t.setPassword(t.getPassword());

		entityManager.persist(t);
	}

	@Override
    public Paciente findByEmail(String email) {
        String query = "FROM Paciente WHERE email = :email";
        try {
            return entityManager.createQuery(query, Paciente.class)
                                .setParameter("email", email)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null; // Si no se encuentra el paciente, retorna null
        }
    }

	@Override
	public Paciente actualizarInformacionContacto(int idPaciente, String nombre, String email, String telefono, String password,
			String direccion) {
		String query = "FROM Paciente WHERE idPaciente = :idPaciente";
		try {
			// Buscar el paciente por ID
			Paciente paciente = entityManager.createQuery(query, Paciente.class)
											 .setParameter("idPaciente", idPaciente)
											 .getSingleResult();
			
			// Actualizar la información si es diferente
			if (!paciente.getNombre().equals(nombre)) {
				paciente.setNombre(nombre);
			}
			if (!paciente.getEmail().equals(email)) {
				if (getCorreos(email).isEmpty()) {
					paciente.setEmail(email);
				}
			}
			if (!paciente.getTelefono().equals(telefono)) {
				paciente.setTelefono(telefono);
			}
			if (!paciente.getPassword().equals(password)) {
				paciente.setPassword(password);
			}
			if (!paciente.getDireccion().equals(direccion)) {
				paciente.setDireccion(direccion);
			}
			entityManager.merge(paciente); // guarda al paciente en la base de datos 
			return paciente; // Devuelve el paciente actualizado
		} catch (NoResultException e) {
			// Manejar la excepción si no se encuentra el paciente
			return null; // O lanzar una excepción
		}
	}
	@Override
	public List<Paciente> getCorreos(String email) {
		String query="FROM Paciente WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Paciente> resultado = entityManager.createQuery(query).setParameter("email", email).getResultList();
		return resultado;   
	}

	

}
