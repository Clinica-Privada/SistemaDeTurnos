package com.cooweb.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

		// Verificar la contraseña (esto depende de si usas BCrypt o comparación
		// directa)
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
		entityManager.remove(profesional);}

    @Override
    public void registrar(Profesional profesional) {
        // Hashear la contraseña antes de guardar
		profesional.setPassword(hashFunction(profesional.getPassword()));

		// Crear nuevo profesional
		Profesional nuevoProfesional = new Profesional(profesional.getNombre(), profesional.getApellido(), profesional.getDni(),
				profesional.getEmail(), profesional.getTelefono(), profesional.getPassword(),
				profesional.getFecha_nacimiento(), profesional.getDireccion());

		entityManager.persist(nuevoProfesional);
	}

    @Override
    public String hashFunction(String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hashFunction'");
    }

    @Override
    public boolean emailYaRegistrado(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'emailYaRegistrado'");
    }

    @Override
    public boolean dniYaRegistrado(String dni) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dniYaRegistrado'");
    }

    @Override
    public List<Profesional> getCorreos(String email) {
        String query = "FROM Profesional WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Profesional> resultado = entityManager.createQuery(query).setParameter("email", email).getResultList();
		return resultado;}

}
