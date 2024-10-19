package com.cooweb.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime; // Para manejar fechas y horas.
import java.time.format.DateTimeFormatter;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

    @RequestMapping(value="eliminarCuenta/{id}", method=RequestMethod.DELETE)
    public void eliminar(@PathVariable int id){
        pacienteDao.eliminar(id);
    }    
    
  @PostMapping(value = "/registrarse")
    public ResponseEntity<?> registrar(@RequestBody Paciente paciente) {
        // Conversión de la fecha de nacimiento
        try {
            String fechaNacimiento = paciente.getFecha_nacimiento();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            paciente.setFecha_nacimiento(fechaNacimiento);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Formato de fecha incorrecto. Debe ser dd-MM-yyyy.");
        }

        // Validación de negocio: número de historia clínica
        if (paciente.getNumero_historia_clinica() <= 0) {
            return ResponseEntity.badRequest().body("El número de historia clínica debe ser un valor positivo.");
        }

        try {
            // Registrar al paciente en la base de datos
            pacienteDao.registrar(paciente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Paciente registrado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el paciente: " + e.getMessage());
        }
    }

    @PostMapping(value="/iniciarSesion")
    public String iniciarSesion(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletRequest request, 
            HttpServletResponse response) {
        
        try {
            // Utilizar el método de PacienteDAO para autenticar al paciente
            Paciente paciente = pacienteDao.iniciarSesion(email, password);

            // Si la autenticación es exitosa, crear la sesión
            HttpSession session = request.getSession(true); // Crear nueva sesión si no existe
            session.setAttribute("paciente", paciente); // Guardar al paciente en la sesión

            // Redirigir a la página principal
            return "redirect:/iniciarSesion"; // Redirige a /home si la autenticación es exitosa

        } catch (RuntimeException e) {
            // Si las credenciales son incorrectas, redirigir al login con un mensaje de error
            return "redirect:/iniciarSesion?error=true"; // Redirige a la página de login si falla
        }
    }

    @PutMapping("api/informacion/{idPaciente}")
    public Paciente actualizarInformacionContacto(
            @PathVariable int idPaciente,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String dni,
            @RequestParam String email,               
            @RequestParam String telefono,
            @RequestParam String password,
            @RequestParam String direccion) {

        // Llamada al método DAO que actualiza la información de contacto del paciente
        Paciente pacienteActualizado = pacienteDao.actualizarInformacionContacto(idPaciente, nombre, apellido, dni, email, telefono, password, direccion);
        
        // Si el paciente no existe (devuelve null), se lanza una excepción
        if (pacienteActualizado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }

        return pacienteActualizado;
    }




}
