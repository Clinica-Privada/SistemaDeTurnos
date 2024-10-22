package com.cooweb.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.cooweb.models.Admin;
import com.cooweb.models.Paciente;
import com.cooweb.models.Admin;
import com.cooweb.models.Reporte;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AdminDAOImp implements AdminDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Admin iniciarSesion(String email, String password) {
        // Consulta para obtener al admin por email
		String query = "FROM Admin WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Admin> admins = entityManager.createQuery(query)
				.setParameter("email", email)
				.getResultList();

		// Verificar si se encontró algún admin con ese email
		if (admins.isEmpty()) {
			throw new RuntimeException("Email no registrado.");
		}

		// Obtener el admin encontrado
		Admin admin = admins.get(0);

		// Verificar la contraseña (esto depende de si usas BCrypt o comparación
		// directa)
		if (!hashFunction(password).equals(admin.getPassword())) {
			throw new RuntimeException("Contraseña incorrecta.");
		}

		// Si la autenticación es exitosa, retorna el admin
		return admin;
    }

    @Override
    public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Unimplemented method 'cerrarSesion'");
    }

    @Override
    public Admin actualizarInformacionContacto(int id_profesional, String nombre, String apellido, String dni,
            String email, String telefono, String password, String direccion) {
        String query = "FROM Admin WHERE id_profesional = :id_profesional";
		try {
			// Buscar el admin por ID
			Admin admin = entityManager.createQuery(query, Admin.class)
					.setParameter("id_profesional", id_profesional)
					.getSingleResult();

			// Actualizar la información si es diferente
			if (Objects.equals(admin.getNombre(), nombre)) {
				admin.setNombre(nombre);
			}
			if (Objects.equals(admin.getApellido(), apellido)) {
				admin.setApellido(apellido);
			}
			if (Objects.equals(admin.getEmail(), email)) {
				if (getCorreos(email).isEmpty()) {
					admin.setEmail(email);
				}
			}
			if (Objects.equals(admin.getTelefono(), telefono)) {
				admin.setTelefono(telefono);
			}
			if (Objects.equals(admin.getPassword(), password)) {
				admin.setPassword(password);
			}
			if (Objects.equals(admin.getDireccion(), direccion)) {
				admin.setDireccion(direccion);
			}
			entityManager.merge(admin); // guarda al admin en la base de datos
			return admin; // Devuelve el admin actualizado
		} catch (NoResultException e) {
			// Manejar la excepción si no se encuentra el admin
			return null; // O lanzar una excepción
		}
    }

    @Override
    public void eliminar(int id) {
        Admin admin = entityManager.find(Admin.class, id);
		if (admin == null) {
			throw new EntityNotFoundException("Admin no encontrado con id: " + id);
		}
		entityManager.remove(admin);}

    @Override
    public void registrar(Admin admin) {
        // Hashear la contraseña antes de guardar
		admin.setPassword(hashFunction(admin.getPassword()));

		// Crear nuevo admin
		Admin nuevoAdmin = new Admin(admin.getNombre(), admin.getApellido(), admin.getDni(),
				admin.getEmail(), admin.getTelefono(), admin.getPassword(),
				admin.getFecha_nacimiento(), admin.getDireccion());

		entityManager.persist(nuevoAdmin);
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
    public List<Admin> getCorreos(String email) {
        String query = "FROM Admin WHERE email = :email";
		@SuppressWarnings("unchecked")
		List<Admin> resultado = entityManager.createQuery(query).setParameter("email", email).getResultList();
		return resultado;
	}
    
    /*
    
    dejamos comentados los metodos que se tienen mayor 
    dificultad por el momento para terminar el proyecto
    
    ----------------------------------------------------
    
    @Override
    public void gestionarServicio() {
        // Aquí se definiría la lógica para crear, editar o eliminar servicios
        System.out.println("Gestionando servicio.");
    }

    @Override
    public void definirHorarioAtencion() {
        // Aquí se definirían los horarios del admin
        System.out.println("Definiendo horario de atención.");
    }
    
    ----------------------------------------------------

    */
    @Override
    public void gestionarUsuario() {
        // Aquí iría la lógica para crear, editar o eliminar usuarios
        System.out.println("Gestionando usuario.");
    }

    @Override
    public void gestionarEspecialidad() {
        // Aquí se podría agregar o eliminar una especialidad
        System.out.println("Gestionando especialidad.");
    }

    @Override
    public void gestionarTurno() {
        // Aquí iría la lógica para gestionar turnos
        System.out.println("Gestionando turno.");
    }

    @Override
    public Reporte recibirReporte() {
        Reporte reporte = new Reporte();
        System.out.println("Recibiendo reporte.");
        return reporte;
    }
    
}
