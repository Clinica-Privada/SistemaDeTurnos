package com.cooweb.models;

import jakarta.persistence.*;
import java.util.Set;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "admin") 
public class Admin extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_admin")
    private int id_admin;

    @ManyToOne
    @JoinColumn(name = "id_especialidad", nullable = false) // La FK hacia Especialidad
    private Especialidad especialidad;

    @OneToMany(mappedBy = "idAdmin") // Relación con Reporte
    private List<Reporte> reportes; // Colección de reportes

    // Constructor
    public Admin() {}

    
    public Admin(String nombre, String apellido, String dni, String email, String telefono, String password,
            String fechaNacimiento, String direccion, int id_admin, Especialidad especialidad, List<Reporte> reportes) {
        super(nombre, apellido, dni, email, telefono, password, fechaNacimiento, direccion);
        this.id_admin = id_admin;
        this.especialidad = especialidad;
        this.reportes = reportes;
    }


    // Getters y Setters
    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    

    public List<Reporte> getReportes() {
        return reportes; // Getter para la lista de reportes
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes; // Setter para la lista de reportes
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
