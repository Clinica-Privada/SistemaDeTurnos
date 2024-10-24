package com.cooweb.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cooweb.models.Paciente;
import com.cooweb.models.Profesional;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Repository
@Transactional
public class ProfesionalDAOImp implements ProfesionalDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Profesional iniciarSesion(String email, String password) {
        // Consulta para obtener al profesional por email
		String query = "FROM Profesional WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Profesional> profesionales = entityManager.createQuery(query)
				.setParameter("email", email)
				.getResultList();

		// Verificar si se encontró algún profesional con ese email
		if (profesionales.isEmpty()) {
			throw new RuntimeException("Email no registrado.");
		}

		// Obtener el profesional encontrado
		Profesional profesional = profesionales.get(0);

		// Verificar la contraseña
		if (!hashFunction(password).equals(profesional.getPassword())) {
			throw new RuntimeException("Contraseña incorrecta.");
		}

		// Si la autenticación es exitosa, retorna el profesional
		return profesional;
    }

    @Override
    public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Unimplemented method 'cerrarSesion'");
    }

    @Override
    public Profesional actualizarInformacionContacto(int id_profesional, String nombre, String apellido, String dni,
            String email, String telefono, String password, String direccion) {
        String query = "FROM Profesional WHERE id_profesional = :id_profesional";
		try {
			// Buscar el profesional por ID
			Profesional profesional = entityManager.createQuery(query, Profesional.class)
					.setParameter("id_profesional", id_profesional)
					.getSingleResult();

			// Actualizar la información si es diferente
			if (Objects.equals(profesional.getNombre(), nombre)) {
				profesional.setNombre(nombre);
			}
			if (Objects.equals(profesional.getApellido(), apellido)) {
				profesional.setApellido(apellido);
			}
			if (Objects.equals(profesional.getEmail(), email)) {
				if (getCorreos(email).isEmpty()) {
					profesional.setEmail(email);
				}
			}
			if (Objects.equals(profesional.getTelefono(), telefono)) {
				profesional.setTelefono(telefono);
			}
			if (Objects.equals(profesional.getPassword(), password)) {
				profesional.setPassword(password);
			}
			if (Objects.equals(profesional.getDireccion(), direccion)) {
				profesional.setDireccion(direccion);
			}
			entityManager.merge(profesional); // guarda al profesional en la base de datos
			return profesional; // Devuelve el profesional actualizado
		} catch (NoResultException e) {
			// Manejar la excepción si no se encuentra el profesional
			return null; // O lanzar una excepción
		}
    }

    @Override
    public void eliminar(int id) {
        Profesional profesional = entityManager.find(Profesional.class, id);
		if (profesional == null) {
			throw new EntityNotFoundException("Profesional no encontrado con id: " + id);
		}
		entityManager.remove(profesional);
	}

	@Override
	public void registrar(Profesional profesional) {
		// Hashear la contraseña antes de guardar
		profesional.setPassword(hashFunction(profesional.getPassword()));
	
		// Persistir el nuevo profesional
		entityManager.persist(profesional);
	}

    @Override
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
    public boolean emailYaRegistrado(String email) {
		String query = "FROM Paciente WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes = entityManager.createQuery(query)
				.setParameter("email", email)
				.getResultList();
		return !pacientes.isEmpty();
	}

	@Override
	public boolean dniYaRegistrado(String dni) {
		String queryDNI = "FROM Paciente WHERE dni = :dni";
		@SuppressWarnings("unchecked")
		List<Paciente> pacientesPorDNI = entityManager.createQuery(queryDNI)
				.setParameter("dni", dni)
				.getResultList();
		return !pacientesPorDNI.isEmpty();
	}

    @Override
    public List<Profesional> getCorreos(String email) {
        String query = "FROM Profesional WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Profesional> resultado = entityManager.createQuery(query).setParameter("email", email).getResultList();
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profesional> getProfesional() {
		String query = "FROM Profesional";
		List<Profesional> resultado = entityManager.createQuery(query).getResultList();
		return resultado;
	}

}
