package com.cooweb.controllers;

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

import com.cooweb.dao.EspecialidadDAO;
import com.cooweb.dao.ProfesionalDAO;
import com.cooweb.models.Especialidad;
import com.cooweb.models.Profesional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class ProfesionalController {

    @Autowired 
    private ProfesionalDAO profesionalDao;
    
    @Autowired
    private EspecialidadDAO especialidadDao;

    @PostMapping(value="/iniciarSesion/profesional")
    public String iniciarSesion(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpServletRequest request, 
            HttpServletResponse response) {
        
        try {
            // Utilizar el método de profesionalDAO para autenticar al profesional
            Profesional profesional = profesionalDao.iniciarSesion(email, password);

            // Si la autenticación es exitosa, crear la sesión
            HttpSession session = request.getSession(true); // Crear nueva sesión si no existe
            session.setAttribute("profesional", profesional); // Guardar al profesional en la sesión

            // Redirigir a la página principal
            return "redirect:/iniciarSesion"; // Redirige a /home si la autenticación es exitosa

        } catch (RuntimeException e) {
            // Si las credenciales son incorrectas, redirigir al login con un mensaje de error
            return "redirect:/iniciarSesion?error=true"; // Redirige a la página de login si falla
        }
    }

    @GetMapping("/cerrarSesion/profesional")
    public String cerrarSesion(HttpServletRequest request) {
        // Obtener la sesión actual
        HttpSession session = request.getSession(false); // No crear una nueva sesión

        if (session != null) {
            session.invalidate(); // Invalida la sesión
        }

        // Redirigir a la página de inicio de sesión o a la página principal
        return "redirect:/iniciarSesion"; // 
    }

    @PutMapping("api/informacion/{id_profesional}")
    public Profesional actualizarInformacionContacto(
            @PathVariable int id_profesional,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String dni,
            @RequestParam String email,               
            @RequestParam String telefono,
            @RequestParam String password,
            @RequestParam String direccion) {

        // Llamada al método DAO que actualiza la información de contacto del profesional
        Profesional profesionalActualizado = profesionalDao.actualizarInformacionContacto(id_profesional, nombre, apellido, dni, email, telefono, password, direccion);
        
        // Si el profesional no existe (devuelve null), se lanza una excepción
        if (profesionalActualizado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "profesional no encontrado");
        }

        return profesionalActualizado;
    }
    @DeleteMapping("/api/profesional/{id}")
    public ResponseEntity<String> eliminarProfesional(@PathVariable int id) {
        try {
            profesionalDao.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profesional no encontrado", e);
        } catch (Exception e) {
            // Captura cualquier otra excepción y devuelves un 500
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping("api/profesional")
    public ResponseEntity<String> registrar(@RequestBody Profesional profesional) {
        // Verifica si el email ya está registrado
        if (profesionalDao.emailYaRegistrado(profesional.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El email ya está registrado.");
        }
        
        // Verifica si el DNI ya está registrado
        if (profesionalDao.dniYaRegistrado(profesional.getDni())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El DNI ya está registrado.");
        }

        // Buscar especialidad por ID
        Especialidad especialidad = especialidadDao.obtenerEspecialidadPorId(profesional.getEspecialidad().getId_especialidad());
        if (especialidad == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Especialidad no encontrada.");
        }

        // Asignar especialidad al profesional
        profesional.setEspecialidad(especialidad);

        // Si todo está bien, registrar al profesional
        profesionalDao.registrar(profesional);

        return ResponseEntity.status(HttpStatus.CREATED).body("Registro exitoso");
    }


}
