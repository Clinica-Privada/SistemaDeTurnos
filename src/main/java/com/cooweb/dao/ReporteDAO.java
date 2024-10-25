package com.cooweb.dao;

import java.util.List;

import com.cooweb.models.Reporte;

public interface ReporteDAO {
	void generarReporte(Reporte reporte);
    List<Reporte> obtenerReportesPorAdmin(Long adminId);

}
