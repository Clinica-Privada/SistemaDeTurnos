package com.cooweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cooweb.models.Admin;
import com.cooweb.models.Reporte;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Repository
public interface AdminDAO{
    
     // Metodos funcionales y utilizados
    Admin iniciarSesion(String email, String password);
    void cerrarSesion(HttpServletRequest request, HttpServletResponse response);

    Admin actualizarInformacionContacto(int id_admin, String nombre, String apellido, String dni, String email, String telefono, String password, String direccion);
    
    void eliminar(int id);
    void registrar(Admin admin);
    List<Admin> getCorreos(String email);
    String hashFunction(String password);
    
    boolean emailYaRegistrado(String email);
    boolean dniYaRegistrado(String dni);

    
    
    /*  
    
    dejamos comentados los metodos que se tienen mayor 
    dificultad por el momento para terminar el proyecto
    
    ----------------------------------------------------

    void gestionarServicio();
    void definirHorarioAtencion();
    
    ----------------------------------------------------

    */
    void gestionarUsuario();
    void gestionarEspecialidad();
    void gestionarTurno();
    Reporte recibirReporte();
}
