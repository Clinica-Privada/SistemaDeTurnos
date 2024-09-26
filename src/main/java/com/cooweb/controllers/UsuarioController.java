package com.cooweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.cooweb.dao.UsuarioDao;
import com.cooweb.models.Usuario;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioDao usuarioDao;
	
	@RequestMapping(value="api/usuarios")
	public List<Usuario> getUsuario(){
		List<Usuario> user=usuarioDao.getUsuarios();
		return user;
	}
}
