package com.cooweb.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cooweb.models.Admin;
import com.cooweb.models.Reporte;

import jakarta.transaction.Transactional;
@Repository
@Transactional
public class AdminDAOImp implements AdminDAO {

    @Override
    public List<Admin> listarTodos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Admin leerPorId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void registrar(Admin t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrar'");
    }

    @Override
    public void actualizar(Admin t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public void eliminar(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public void gestionarUsuario() {
        // Aquí iría la lógica para crear, editar o eliminar usuarios
        System.out.println("Gestionando usuario.");
    }
    /*
    
    dejamos comentados los metodos que se tienen mayor 
    dificultad por el momento para terminar el proyecto
    
    ----------------------------------------------------
    
    @Override
    public void gestionarServicio() {
        // Aquí se definiría la lógica para crear, editar o eliminar servicios
        System.out.println("Gestionando servicio.");
    }

    @Override
    public void definirHorarioAtencion() {
        // Aquí se definirían los horarios del profesional
        System.out.println("Definiendo horario de atención.");
    }
    
    ----------------------------------------------------

    */

    @Override
    public void gestionarEspecialidad() {
        // Aquí se podría agregar o eliminar una especialidad
        System.out.println("Gestionando especialidad.");
    }

    @Override
    public void gestionarTurno() {
        // Aquí iría la lógica para gestionar turnos
        System.out.println("Gestionando turno.");
    }

    @Override
    public Reporte recibirReporte() {
        Reporte reporte = new Reporte();
        System.out.println("Recibiendo reporte.");
        return reporte;
    }
    
}
