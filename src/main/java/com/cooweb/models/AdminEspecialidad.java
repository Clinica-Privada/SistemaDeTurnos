package com.cooweb.models;

import jakarta.persistence.*;

@Entity
@Table(name = "adminespecialidad")
public class AdminEspecialidad {

    @EmbeddedId
    private AdminEspecialidadId id;

    @ManyToOne
    @MapsId("id_admin") // Indica que esta relación está vinculada a la clave compuesta
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin") // Columna en la tabla de Admin
    private Admin admin;

    @ManyToOne
    @MapsId("id_especialidad") // Indica que esta relación está vinculada a la clave compuesta
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad") // Columna en la tabla de Especialidad
    private Especialidad especialidad;

    // Constructores, getters y setters

    public AdminEspecialidad() {}

    public AdminEspecialidad(AdminEspecialidadId id, Admin admin, Especialidad especialidad) {
        this.id = id;
        this.admin = admin;
        this.especialidad = especialidad;
    }

    public AdminEspecialidadId getId() {
        return id;
    }

    public void setId(AdminEspecialidadId id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}
