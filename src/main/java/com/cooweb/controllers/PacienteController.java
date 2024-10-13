package com.cooweb.controllers;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public void eliminar(@PathVariable int id){
        pacienteDao.eliminar(id);
    }    
    
    @RequestMapping(value="api/pacientes", method=RequestMethod.POST)
    public void registrar(@RequestBody Paciente paciente){
        pacienteDao.registrar(paciente);
    }

    
    @PostMapping("/login")
    public ResponseEntity<Paciente> iniciarSesion(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
    
        System.out.println("Intentando iniciar sesión con el email: " + email);  // Mensaje de depuración
    
        // Consulta el paciente por email
        Paciente paciente = pacienteDao.findByEmail(email);
        if (paciente == null) {
            System.out.println("");  // Mensaje de depuración
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Email no registrado
        }
    
        // Verifica que la contraseña coincida sin encriptación
        if (!password.equals(paciente.getPassword())) {
            System.out.println("Contraseña incorrecta.");  // Mensaje de depuración
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Contraseña incorrecta
        }
    
        // Si la autenticación es exitosa, retorna el paciente
        System.out.println("Inicio de sesión exitoso.");  // Mensaje de depuración
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("api/{idPaciente}/contacto")
    public Paciente actualizarInformacionContacto(
            @PathVariable int idPaciente,
            @RequestParam String nombre,
            @RequestParam String email,               
            @RequestParam String telefono,
            @RequestParam String password,
            @RequestParam String direccion) {

        // Llamada al método DAO que actualiza la información de contacto del paciente
        Paciente pacienteActualizado = pacienteDao.actualizarInformacionContacto(idPaciente, nombre, email, telefono, password, direccion);
        
        // Si el paciente no existe (devuelve null), se lanza una excepción
        if (pacienteActualizado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }

        return pacienteActualizado;
    }




}
