package com.cooweb.models;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProfesionalEspecialidadId implements Serializable {
    
    private int idProfesional;
    private int idEspecialidad;

    public ProfesionalEspecialidadId() {}

    public ProfesionalEspecialidadId(int idProfesional, int idEspecialidad) {
        this.idProfesional = idProfesional;
        this.idEspecialidad = idEspecialidad;
    }

    // Getters y Setters

    public int getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    // Implementa equals() y hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfesionalEspecialidadId that = (ProfesionalEspecialidadId) o;
        return idProfesional == that.idProfesional &&
                idEspecialidad == that.idEspecialidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProfesional, idEspecialidad);
    }
}

