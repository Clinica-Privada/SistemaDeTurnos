package com.cooweb.controllers;

import com.cooweb.dao.EspecialidadDAO;
import com.cooweb.models.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidad")
public class EspecialidadController {

    @Autowired
    private EspecialidadDAO especialidadDAO;

    @GetMapping
    public List<Especialidad> listarEspecialidades() {
        return especialidadDAO.listarEspecialidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> obtenerEspecialidad(@PathVariable int id) {
        Especialidad especialidad = especialidadDAO.obtenerEspecialidadPorId(id);
        if (especialidad != null) {
            return new ResponseEntity<>(especialidad, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> registrarEspecialidad(@RequestBody Especialidad especialidad) {
        especialidadDAO.registrarEspecialidad(especialidad);
        return new ResponseEntity<>("Especialidad registrada con éxito", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEspecialidad(@PathVariable int id, @RequestBody Especialidad especialidad) {
        especialidad.setId_especialidad(id);
        especialidadDAO.actualizarEspecialidad(especialidad);
        return new ResponseEntity<>("Especialidad actualizada con éxito", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEspecialidad(@PathVariable int id) {
        especialidadDAO.eliminarEspecialidad(id);
        return new ResponseEntity<>("Especialidad eliminada con éxito", HttpStatus.OK);
    }
}
