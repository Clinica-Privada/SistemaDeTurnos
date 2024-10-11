package com.cooweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import com.cooweb.dao.UsuarioDAO;
import com.cooweb.models.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@RequestMapping(value="api/usuarios")
	public List<Usuario> listarTodos(){
		List<Usuario> user=usuarioDao.listarTodos();
		return user;
	}
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST) 
	public Usuario login(@RequestParam String email, @RequestParam String contraseña) {
    Usuario usuario = usuarioDao.iniciarSesion(email, contraseña);
    if (usuario == null) {
        // Retornar un error si las credenciales no son correctas
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
    }
    return usuario;


}

}
