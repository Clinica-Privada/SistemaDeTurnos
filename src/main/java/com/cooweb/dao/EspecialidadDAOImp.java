package com.cooweb.dao;

import com.cooweb.models.Especialidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class EspecialidadDAOImp implements EspecialidadDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Especialidad> listarEspecialidades() {
        String query = "FROM Especialidad";
        return entityManager.createQuery(query, Especialidad.class).getResultList();
    }

    @Override
    public Especialidad obtenerEspecialidadPorId(int id_especialidad) {
        return entityManager.find(Especialidad.class, id_especialidad);
    }

    @Override
    public void registrarEspecialidad(Especialidad especialidad) {
        entityManager.persist(especialidad);
    }

    @Override
    public void actualizarEspecialidad(Especialidad especialidad) {
        entityManager.merge(especialidad);
    }

    @Override
    public void eliminarEspecialidad(int id_especialidad) {
        Especialidad especialidad = obtenerEspecialidadPorId(id_especialidad);
        if (especialidad != null) {
            entityManager.remove(especialidad);
        }
    }
}
