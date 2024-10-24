package com.cooweb.dao;

import java.util.List;
import com.cooweb.models.Especialidad;

public interface EspecialidadDAO {
    List<Especialidad> listarEspecialidades(); // Obtener todas las especialidades
    Especialidad obtenerEspecialidadPorId(int id_especialidad); // Obtener especialidad por ID
    void registrarEspecialidad(Especialidad especialidad); // Registrar nueva especialidad
    void actualizarEspecialidad(Especialidad especialidad); // Actualizar especialidad
    void eliminarEspecialidad(int id_especialidad); // Eliminar especialidad por ID
}
