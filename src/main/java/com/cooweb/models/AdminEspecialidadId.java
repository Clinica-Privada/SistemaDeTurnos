package com.cooweb.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AdminEspecialidadId implements Serializable {
    
    @Column(name = "idAdmin")
    private int idAdmin;

    @Column(name = "idEspecialidad")
    private int idEspecialidad;

    public AdminEspecialidadId() {}

    public AdminEspecialidadId(int idAdmin, int idEspecialidad) {
        this.idAdmin = idAdmin;
        this.idEspecialidad = idEspecialidad;
    }

    // Getters y Setters

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    // Implementación de hashCode y equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminEspecialidadId)) return false;
        AdminEspecialidadId that = (AdminEspecialidadId) o;
        return idAdmin == that.idAdmin && idEspecialidad == that.idEspecialidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdmin, idEspecialidad);
    }
}
