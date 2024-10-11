package com.cooweb.dao;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cooweb.models.Admin;
import com.cooweb.models.Reporte;
@Repository
public interface AdminDAO extends CRUD<Admin>{
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
