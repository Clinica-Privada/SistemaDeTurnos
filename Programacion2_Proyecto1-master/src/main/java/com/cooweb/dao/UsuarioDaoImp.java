package com.cooweb.dao;

import org.springframework.stereotype.Repository;

import com.cooweb.models.Usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;



@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Usuario> getUsuarios(){
		String query="from Usuario";
		List<Usuario> resultado= entityManager.createQuery(query).getResultList();
		return resultado;
	}
}
