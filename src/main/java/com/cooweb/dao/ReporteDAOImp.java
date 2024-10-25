package com.cooweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cooweb.models.Reporte;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ReporteDAOImp {
	 @PersistenceContext
	    private EntityManager entityManager;

	    public void generarReporte(Reporte reporte) {
	        entityManager.persist(reporte);
	    }

	    @SuppressWarnings("unchecked")
	    public List<Reporte> obtenerReportesPorAdmin(Long adminId) {
	        String query = "SELECT r FROM Reporte r WHERE r.admin.id = :adminId";
	        return entityManager.createQuery(query)
	                            .setParameter("adminId", adminId)
	                            .getResultList();
	    }

}
