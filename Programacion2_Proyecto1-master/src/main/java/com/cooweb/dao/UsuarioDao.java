package com.cooweb.dao;

import jakarta.transaction.Transactional;
import java.util.List;
import com.cooweb.models.Usuario;

@Transactional
public interface UsuarioDao {
	List<Usuario> getUsuarios();
}
