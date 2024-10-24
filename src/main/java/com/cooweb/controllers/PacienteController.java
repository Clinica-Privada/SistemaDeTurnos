package com.cooweb.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import jakarta.persistence.EntityNotFoundException;
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

    @RequestMapping(value="api/pacientes", method=RequestMethod.POST)
    public ResponseEntity<String> registrar(@RequestBody Paciente paciente) {
            // Verifica si el email ya está registrado
        if(pacienteDao.emailYaRegistrado(paciente.getEmail())) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
        .body("El email ya está registrado.");
        }

        // Verifica si el DNI ya está registrado
        if(pacienteDao.dniYaRegistrado(paciente.getDni())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body("El DNI ya está registrado.");
        }

        // Si todo está bien, registrar al paciente
        pacienteDao.registrar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
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
    
    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpServletRequest request) {
        // Obtener la sesión actual
        HttpSession session = request.getSession(false); // No crear una nueva sesión

        if (session != null) {
            session.invalidate(); // Invalida la sesión
        }

        // Redirigir a la página de inicio de sesión o a la página principal
        return "redirect:/iniciarSesion"; // 
    }

    @PutMapping("api/informacion/{id_paciente}")
    public Paciente actualizarInformacionContacto(
            @PathVariable int id_paciente,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String dni,
            @RequestParam String email,               
            @RequestParam String telefono,
            @RequestParam String password,
            @RequestParam String direccion) {

        // Llamada al método DAO que actualiza la información de contacto del paciente
        Paciente pacienteActualizado = pacienteDao.actualizarInformacionContacto(id_paciente, nombre, apellido, dni, email, telefono, password, direccion);
        
        // Si el paciente no existe (devuelve null), se lanza una excepción
        if (pacienteActualizado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado");
        }

        return pacienteActualizado;
    }

    @DeleteMapping("/api/pacientes/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable int id) {
        try {
            pacienteDao.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado", e);
        } catch (Exception e) {
            // Captura cualquier otra excepción y devuelves un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
