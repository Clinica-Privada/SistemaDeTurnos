package com.cooweb.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AdminEspecialidadId implements Serializable {
    
    @Column(name = "id_admin")
    private int id_admin;

    @Column(name = "id_especialidad")
    private int id_especialidad;

    public AdminEspecialidadId() {}

    public AdminEspecialidadId(int id_admin, int id_especialidad) {
        this.id_admin = id_admin;
        this.id_especialidad = id_especialidad;
    }

    // Getters y Setters

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    // Implementación de hashCode y equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminEspecialidadId)) return false;
        AdminEspecialidadId that = (AdminEspecialidadId) o;
        return id_admin == that.id_admin && id_especialidad == that.id_especialidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_admin, id_especialidad);
    }
}
