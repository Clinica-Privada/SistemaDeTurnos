package com.cooweb.dao;
import java.sql.Time;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cooweb.models.Admin;
import com.cooweb.models.Especialidad;
import com.cooweb.models.IntervaloHorario;
import com.cooweb.models.Paciente;
import com.cooweb.models.Profesional;
import com.cooweb.models.Turno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository  // Indica que esta clase es un componente de acceso a datos en Spring


public class TurnoDAOImp implements TurnoDAO {
	


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void crearTurno(Turno turno) {
        entityManager.persist(turno);
    }

    @Override
    @Transactional
    public void confirmarAsistencia(int idTurno) {
        Turno turno = obtenerTurnoPorId(idTurno);
        if (turno != null) {
            turno.setEstado_turno(Turno.EstadoTurno.CONFIRMADO);
            entityManager.merge(turno);
        }
    }
 //maybe replace/repensar esta? 
    @Override
    @Transactional
    public void reprogramarTurno(int idTurno, Turno turnoActualizado) {
        Turno turno = obtenerTurnoPorId(idTurno);
        if (turno != null) {
            turno.setFecha_turno(turnoActualizado.getFecha_turno());
            turno.setHora_turno(turnoActualizado.getHora_turno());
            turno.setPaciente(turnoActualizado.getPaciente());
            turno.setProfesional(turnoActualizado.getProfesional());
            turno.setEspecialidad(turnoActualizado.getEspecialidad());
            turno.setAdmin(turnoActualizado.getAdmin());
            turno.setEstado_turno(turnoActualizado.getEstado_turno());
            entityManager.merge(turno);
        }
    }

    @Override
    public Turno obtenerTurnoPorId(int idTurno) {
        return entityManager.find(Turno.class, idTurno);
    }

    @Override
   
    public List<Turno> obtenerTodosLosTurnos() { 
    return entityManager.createQuery("SELECT t FROM Turno t", Turno.class).getResultList();

    }

    @Override
    @Transactional
    public void actualizarTurno(Turno turno) {
        entityManager.merge(turno);
    }
    
    @Override
    public void gestionarTurno(Admin admin) {
        // Implementa la lógica necesaria para gestionar turnos
    }

    @Override
    public void consultarTurno(Profesional profesional) {
        // Implementa la lógica necesaria para que un profesional consulte sus turnos
    }

    //metodos relacionados con paciente
     
     
        @Override
    @Transactional
    public Turno agendarTurno(Paciente paciente, Date fecha_turno, Time hora_turno, Profesional profesional, Especialidad especialidad) {
        // Verificar si hay intervalos horarios disponibles para el profesional y la fecha seleccionada
        List<IntervaloHorario> intervalosDisponibles = obtenerIntervalosDisponibles(profesional, fecha_turno);

        // Comprobar si la hora seleccionada está en los intervalos disponibles
        boolean disponible = intervalosDisponibles.stream()
                .anyMatch(intervalo -> 
                    hora_turno.after(intervalo.getHoraInicio()) && hora_turno.before(intervalo.getHoraFin()));

        if (!disponible) {
            throw new RuntimeException("La hora seleccionada no está disponible.");
        }

        Turno nuevoTurno = new Turno(); // Crear un nuevo turno
        nuevoTurno.setFecha_turno( fecha_turno);
        nuevoTurno.setHora_turno(hora_turno);
        nuevoTurno.setPaciente(paciente);
        nuevoTurno.setProfesional(profesional);
        nuevoTurno.setEspecialidad(especialidad);

        crearTurno(nuevoTurno); // Persistir el nuevo turno
        return nuevoTurno;
    }

    // Método adicional para obtener los intervalos disponibles
    private List<IntervaloHorario> obtenerIntervalosDisponibles(Profesional profesional, Date fecha) {
        // Implementar lógica para consultar los intervalos horarios del profesional para una fecha específica
        return entityManager.createQuery("SELECT i FROM IntervaloHorario i WHERE i.profesional = :profesional AND i.fecha = :fecha", IntervaloHorario.class)
                .setParameter("profesional", profesional)
                .setParameter("fecha", fecha)
                .getResultList();
    }


    
      @Override
    @Transactional
    public void cancelarTurno(int idTurno) {
        Turno turno = entityManager.find(Turno.class, idTurno);
        if (turno != null) {
            entityManager.remove(turno);
        }
    }
@Override

    public void consultarTurno(Paciente paciente) {
        // Implementa la lógica para que el paciente consulte sus turnos
    }

    @Override
    public void suscribirseNotificaciones(Paciente paciente) {
        // Implementa la lógica para suscribirse a notificaciones
    }

    @Override
    public void consultarHistorial(Paciente paciente) {
        // Implementa la lógica para consultar el historial de turnos
    }



}




