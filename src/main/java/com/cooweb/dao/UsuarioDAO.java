package com.cooweb.dao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Repository;

import com.cooweb.models.Usuario;

@Repository
public interface UsuarioDAO extends CRUD<Usuario>{
	
	Usuario iniciarSesion(String email, String password);
	void cerrarSesion(HttpServletRequest request, HttpServletResponse response);
}
