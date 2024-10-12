package com.cooweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cooweb.models.Paciente;

import com.cooweb.dao.PacienteDAO;

@RestController
public class PacienteController {


    @Autowired
    private PacienteDAO pacienteDao;

    @GetMapping("api/pacientes")
    public List<Paciente> getPacientes(){
        List<Paciente> user = pacienteDao.getPacientes();
        return user;
    }

    @RequestMapping(value="api/pacientes/{id}", method=RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        pacienteDao.eliminar(id);
    }
    @RequestMapping(value="api/pacientes", method=RequestMethod.POST)
    public void registrarUsuario(@RequestBody Paciente paciente){
        pacienteDao.registrar(paciente);
    }





}
