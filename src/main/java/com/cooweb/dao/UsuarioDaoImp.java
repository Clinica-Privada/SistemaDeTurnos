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
	
	@Override
	public List<Usuario> listarTodos() {
		String query="from Usuario";
		List<Usuario> resultado= entityManager.createQuery(query).getResultList();
		return resultado;
	}
	@Override
	public Usuario leerPorId(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'leerPorId'");
	}

	// Registro
	@Override
	public void registrar(Usuario t) {
		String query ="FROM Usuario WHERE email= :email";
		List<Usuario> usuarios = entityManager.createQuery(query)
								.setParameter("email", t.getEmail())
								.getResultList();
		if(!usuarios.isEmpty()){
			throw new RuntimeException("El email ya está registrado.");
		}
		// Validar que el DNI no esté ya registrado
		String queryDNI = "FROM Usuario WHERE dni = :dni";
		List<Usuario> usuariosPorDNI = entityManager.createQuery(queryDNI)
								.setParameter("dni", t.getDni())
								.getResultList();
		
		if (!usuariosPorDNI.isEmpty()) {
			throw new RuntimeException("El DNI ya está registrado.");
		}
		//Hashear la contraseña antes de guardar
		String hashedContraseña = BCrypt.hashpw(t.getContraseña(), BCrypt.gensalt());
		t.setContraseña(hashedContraseña);

		entityManager.persist(t);
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

	//Iniciar Sesion
	@Override
	public Usuario iniciarSesion(String email, String contraseña) {
		String query = "FROM Usuario WHERE email = :email AND password = :password";
		List<Usuario> usuarios = entityManager.createQuery(query)
								.setParameter("email", email)
								.setParameter("password", contraseña)
								.getResultList();
		if (usuarios.isEmpty()){
			return null; // No se encontro el usuario o las credenciales son incorrectas
		}
		Usuario usuario = usuarios.get(0);
   		// Comparar el hash de la contraseña almacenada con la ingresada
    	if (BCrypt.checkpw(contraseña, usuario.getContraseña())) {
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
		
}
