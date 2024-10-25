package com.cooweb.controllers;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cooweb.dao.TurnoDAO;
import com.cooweb.models.Especialidad;
import com.cooweb.models.Paciente;
import com.cooweb.models.Profesional;
import com.cooweb.models.Turno;

import jakarta.persistence.EntityManager;
 //segunda prueba d esubir el puto controlsdor de mierda


@RestController
@RequestMapping("/api/turnos")


public class TurnoController {
	@Autowired
    private TurnoDAO turnoDAO;
	private EntityManager entityManager;

    // Crear un nuevo turno
    @PostMapping
    public void crearTurno(@RequestBody Turno turno) {
        turnoDAO.crearTurno(turno);
    }
    //agendar un nuevo turno
    @PostMapping("/agendar")
    public Turno agendarTurno(@RequestParam("idPaciente") int idPaciente,
                               @RequestParam("fecha_turno") Date fechaTurno,
                               @RequestParam("hora_turno") Time horaTurno,
                               @RequestParam("idProfesional") int idProfesional,
                               @RequestParam("especialidad") Especialidad especialidad) {
        // Recuperar el paciente y profesional de la base de datos usando sus IDs
        Paciente paciente = entityManager.find(Paciente.class, idPaciente);
        Profesional profesional = entityManager.find(Profesional.class, idProfesional);

        if (paciente == null || profesional == null) {
            throw new RuntimeException("Paciente o profesional no encontrado.");
        }

      

		
		// Agendar el turno utilizando el método del DAO
        return turnoDAO.agendarTurno(paciente, fechaTurno, horaTurno,  profesional,  especialidad);
    }


    // Confirmar asistencia a un turno
    @PostMapping("/{idTurno}/confirmar")
    public void confirmarAsistencia(@PathVariable int idTurno) {
        turnoDAO.confirmarAsistencia(idTurno);
    }

    // Reprogramar un turno
    @PutMapping("/{idTurno}/reprogramar")
    public void reprogramarTurno(@PathVariable int idTurno, @RequestBody Turno turnoActualizado) {
        turnoDAO.reprogramarTurno(idTurno, turnoActualizado);
    }

    // Obtener un turno por ID
    @GetMapping("/{idTurno}")
    public Turno obtenerTurnoPorId(@PathVariable int idTurno) {
        return turnoDAO.obtenerTurnoPorId(idTurno);
    }

    // Obtener todos los turnos
    @GetMapping
    public List<Turno> obtenerTodosLosTurnos() {
        return turnoDAO.obtenerTodosLosTurnos();
    }

    // Actualizar un turno
    @PutMapping("/{idTurno}")
    public void actualizarTurno(@PathVariable int idTurno, @RequestBody Turno turno) {
        turno.setId_turno(idTurno); // Aseguramos que se setea el ID correcto
        turnoDAO.actualizarTurno(turno);
    }

}


