package com.cooweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooweb.models.Paciente;
import com.cooweb.models.Usuario;
import com.cooweb.dao.PacienteDAO;

@RestController
public class PacienteController {

@Autowired
private PacienteDAO pacienteDao;



@RequestMapping(value="api/pacientes")

public void cancelarTurno(){
    
}


}
