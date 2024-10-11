package com.cooweb.models;

import jakarta.persistence.*;
import java.util.Set;
import java.util.List;

@Entity
@Table(name = "admin") 
public class Admin extends Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAdmin")
    private int idAdmin;
    @OneToMany(mappedBy = "admin")
    private Set<AdminEspecialidad> adminEspecialidades;

    @OneToMany(mappedBy = "admin") // Relación con Reporte
    private List<Reporte> reportes; // Colección de reportes

    // Constructor
    public Admin() {}

    // Getters y Setters
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Set<AdminEspecialidad> getAdminEspecialidades() {
        return adminEspecialidades;
    }

    public void setAdminEspecialidades(Set<AdminEspecialidad> adminEspecialidades) {
        this.adminEspecialidades = adminEspecialidades;
    }

    public List<Reporte> getReportes() {
        return reportes; // Getter para la lista de reportes
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes; // Setter para la lista de reportes
    }
}
