package com.cooweb.dao;

import java.util.List;


import com.cooweb.models.Profesional;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;


@Transactional
public interface ProfesionalDAO{
     // Metodos funcionales y utilizados
    Profesional iniciarSesion(String email, String password);
    void cerrarSesion(HttpServletRequest request, HttpServletResponse response);

    Profesional actualizarInformacionContacto(int id_profesional, String nombre, String apellido, String dni, String email, String telefono, String password, String direccion);
    
    void eliminar(int id);
    void registrar(Profesional profesional);
    List<Profesional> getCorreos(String email);
    String hashFunction(String password);
    
    boolean emailYaRegistrado(String email);
    boolean dniYaRegistrado(String dni);

    
    
}
