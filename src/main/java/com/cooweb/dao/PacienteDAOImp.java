package com.cooweb.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import com.cooweb.models.Paciente;
import com.cooweb.models.Turno;

@Repository
@Transactional
public class PacienteDAOImp implements PacienteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Paciente iniciarSesion(String email, String password) {
		// Consulta para obtener al paciente por email
		String query = "FROM Paciente WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes = entityManager.createQuery(query)
				.setParameter("email", email)
				.getResultList();

		// Verificar si se encontró algún paciente con ese email
		if (pacientes.isEmpty()) {
			throw new RuntimeException("Email no registrado.");
		}

		// Obtener el paciente encontrado
		Paciente paciente = pacientes.get(0);

		// Verificar la contraseña (esto depende de si usas BCrypt o comparación
		// directa)
		if (!hashFunction(password).equals(paciente.getPassword())) {
			throw new RuntimeException("Contraseña incorrecta.");
		}

		// Si la autenticación es exitosa, retorna el paciente
		return paciente;
	}
	@Override
	public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException("Error al cerrar sesion");
	}

	

	@Override
	public Turno agendarTurno(Paciente paciente, Date fecha_turno, Time hora_turno) {
		Turno nuevoTurno = new Turno();
		nuevoTurno.setFecha_turno(fecha_turno);
		nuevoTurno.setHora_turno(hora_turno);
		nuevoTurno.setPaciente(paciente);
		// Guarda el nuevo turno en la base de datos
		entityManager.persist(nuevoTurno);
		return nuevoTurno; // Retorna el turno agendado
	}

	@Override
	public void consultarTurno() {
		
		throw new UnsupportedOperationException("Unimplemented method 'consultarTurno'");
	}

	@Override
	public void cancelarTurno() {
		
		throw new UnsupportedOperationException("Unimplemented method 'cancelarTurno'");
	}
	

	@Override
	public void suscribirseNotificaciones() {
		
		throw new UnsupportedOperationException("Unimplemented method 'suscribirseNotificaciones'");
	}

	@Override
	public void consultarHistorial() {
		
		throw new UnsupportedOperationException("Unimplemented method 'consultarHistorial'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> getPacientes() {
		String query = "FROM Paciente";
		List<Paciente> resultado = entityManager.createQuery(query).getResultList();
		return resultado;
	}

	@Override
	public void eliminar(int id) {
		Paciente paciente = entityManager.find(Paciente.class, id);
		if (paciente == null) {
			throw new EntityNotFoundException("Paciente no encontrado con id: " + id);
		}
		entityManager.remove(paciente);
	}

	@Override
	public void registrar(Paciente paciente) {
		// Hashear la contraseña antes de guardar
		paciente.setPassword(hashFunction(paciente.getPassword()));

		// Crear nuevo paciente
		Paciente nuevoPaciente = new Paciente(paciente.getNombre(), paciente.getApellido(), paciente.getDni(),
				paciente.getEmail(), paciente.getTelefono(), paciente.getPassword(),
				paciente.getFecha_nacimiento(), paciente.getDireccion());

		entityManager.persist(nuevoPaciente);
	}

	public boolean emailYaRegistrado(String email) {
		String query = "FROM Paciente WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes = entityManager.createQuery(query)
				.setParameter("email", email)
				.getResultList();
		return !pacientes.isEmpty();
	}

	public boolean dniYaRegistrado(String dni) {
		String queryDNI = "FROM Paciente WHERE dni = :dni";
		@SuppressWarnings("unchecked")
		List<Paciente> pacientesPorDNI = entityManager.createQuery(queryDNI)
				.setParameter("dni", dni)
				.getResultList();
		return !pacientesPorDNI.isEmpty();
	}

	public String hashFunction(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedBytes = md.digest(password.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error al hashear la contraseña", e);
		}
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
	public Paciente actualizarInformacionContacto(int id_paciente, String nombre, String apellido, String dni,
			String email, String telefono, String password,
			String direccion) {
		String query = "FROM Paciente WHERE id_paciente = :id_paciente";
		try {
			// Buscar el paciente por ID
			Paciente paciente = entityManager.createQuery(query, Paciente.class)
					.setParameter("id_paciente", id_paciente)
					.getSingleResult();

			// Actualizar la información si es diferente
			if (Objects.equals(paciente.getNombre(), nombre)) {
				paciente.setNombre(nombre);
			}
			if (Objects.equals(paciente.getApellido(), apellido)) {
				paciente.setApellido(apellido);
			}
			if (Objects.equals(paciente.getEmail(), email)) {
				if (getCorreos(email).isEmpty()) {
					paciente.setEmail(email);
				}
			}
			if (Objects.equals(paciente.getTelefono(), telefono)) {
				paciente.setTelefono(telefono);
			}
			if (Objects.equals(paciente.getPassword(), password)) {
				paciente.setPassword(password);
			}
			if (Objects.equals(paciente.getDireccion(), direccion)) {
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
		String query = "FROM Paciente WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Paciente> resultado = entityManager.createQuery(query).setParameter("email", email).getResultList();
		return resultado;
	}

	@Override
	public Set<Turno> consultarTurnos(int id_paciente) {
		String query = "SELECT FROM Turno t WHERE t.paciente.id_paciente = :id_paciente";
		@SuppressWarnings("unchecked")
		Set<Turno> turnos = new HashSet<>(entityManager.createQuery(query).setParameter("id_paciente", id_paciente).getResultList());
		return turnos;
	}

}
