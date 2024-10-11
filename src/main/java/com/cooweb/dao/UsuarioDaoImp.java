package com.cooweb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.cooweb.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.util.List;



@Repository
@Transactional
public class UsuarioDAOImp implements UsuarioDAO{
	@PersistenceContext
	private EntityManager entityManager;
	/* 
	@Override
	public List<Usuario> listarTodos() {
		String query="from Usuario";
		List<Usuario> resultado= entityManager.createQuery(query).getResultList();
		return resultado;
	}
	@Override
	public Usuario leerPorId(int id) {

			Usuario usuario = entityManager.find(Usuario.class, id);
			if(usuario == null){
				throw new RuntimeException("id "+id+" no encontrado.");
			}
			return usuario;
		
	}

	// Registro
	
	@Override
	public void registrar(Usuario t) {
		
		// Valida que el email no esté registrado
		String query ="FROM Usuario WHERE email= :email";
		List<Usuario> usuarios = entityManager.createQuery(query)
								.setParameter("email", t.getEmail())
								.getResultList();
		if(!usuarios.isEmpty()){
			throw new RuntimeException("El email ya está registrado.");
		}

		// Valida que el DNI no esté ya registrado
		String queryDNI = "FROM Usuario WHERE dni = :dni";
		List<Usuario> usuariosPorDNI = entityManager.createQuery(queryDNI)
								.setParameter("dni", t.getId())
								.getResultList();
		
		if (!usuariosPorDNI.isEmpty()) {
			throw new RuntimeException("El DNI ya está registrado.");
		}
		//Hashear la contraseña antes de guardar
		String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
		t.setPassword(hashedPassword);

		entityManager.persist(t);
	}

	@Override
	public void actualizar(Usuario usuarioActualizado) {
		/* 
		--------------------------------------------------------
		Agregar coneccion aqui para entender que el usuario esta logeado, caso contrario no deberia dejar hacer nada

		Buscar el usuario existente en la base de datos por su ID
		--------------------------------------------------------
		
		 Usuario usuarioExistente = entityManager.find(Usuario.class, usuarioActualizado.getId());
		
		if (usuarioExistente == null) {
			throw new RuntimeException("Usuario no encontrado.");
		}
	
		// Validar que el nuevo email no esté en uso por otro usuario
		String query = "FROM Usuario WHERE email = :email AND id != :id";
		List<Usuario> usuariosConMismoEmail = entityManager.createQuery(query)
											.setParameter("email", usuarioActualizado.getEmail())
											.setParameter("id", usuarioActualizado.getId())
											.getResultList();
	
		if (!usuariosConMismoEmail.isEmpty()) {
			throw new RuntimeException("El email ya está en uso por otro usuario.");
		}
	
		// Actualizar solo los datos que sean permitidos
		usuarioExistente.setNombre(usuarioActualizado.getNombre());
		usuarioExistente.setEmail(usuarioActualizado.getEmail());
		usuarioExistente.setDireccion(usuarioActualizado.getDireccion());
	
		// Si la contraseña ha cambiado, se hashea la nueva contraseña
		if (!usuarioActualizado.getPassword().isEmpty()) {
			String hashedPassword = BCrypt.hashpw(usuarioActualizado.getPassword(), BCrypt.gensalt());
			usuarioExistente.setPassword(hashedPassword);
		}
	
		// Guardar los cambios en la base de datos
		entityManager.merge(usuarioExistente);
	}
	
	@Override
	public void eliminar(int id) {
		try{
			Usuario usuario = entityManager.find(Usuario.class,id);
			if (usuario == null){
				throw new RuntimeException("Usuario no encontrado.");
			}
			entityManager.remove(usuario);
		}catch(Exception e){
			throw new RuntimeException("Error al intentar eliminar el usuario" + e.getMessage());
		}
	}

	//Iniciar Sesion
	@Override
	public Usuario iniciarSesion(String email, String password) {
		String query = "FROM Usuario WHERE email = :email";
		List<Usuario> usuarios = entityManager.createQuery(query)
							.setParameter("email", email)
							.getResultList();
		if (usuarios.isEmpty()) {
			return null; // No se encontró el usuario
		}
		Usuario usuario = usuarios.get(0);
		if (BCrypt.checkpw(password, usuario.getPassword())) {
			return usuario; // Inicio de sesión exitoso
		} else {
			return null; // Contraseña incorrecta
		}
	}
	@Override
	public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
		// Obtener el usuario autenticado
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// Cerrar la sesion
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		// Otras lógicas de cierre de sesión, si es necesario
		System.out.println("Sesión cerrada.");
	}


    */

	@Override
	public Usuario iniciarSesion(String email, String password) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iniciarSesion'");
	}

	@Override
	public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cerrarSesion'");
	}

	@Override
	public List<Usuario> listarTodos() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'listarTodos'");
	}

	@Override
	public Usuario leerPorId(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'leerPorId'");
	}

	@Override
	public void registrar(Usuario t) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'registrar'");
	}

	@Override
	public void actualizar(Usuario t) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
	}

	@Override
	public void eliminar(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
	}

	
	
}
