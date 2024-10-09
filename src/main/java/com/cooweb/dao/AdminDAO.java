package com.cooweb.dao;
import jakarta.transaction.Transactional;
import java.util.List;
import com.cooweb.models.Admin;

public interface AdminDAO extends CRUD<Admin>{
    void gestionarUsuario();
    void gestionarServicio();
    void definirHorarioAtencion();
    void gestionarEspecialidad();
    void gestionarTurno();
    void recibirReporte();
}
