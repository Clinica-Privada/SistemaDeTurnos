package com.cooweb.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AdminTurnoId implements Serializable {
    @Column(name = "id_admin")
    private int id_admin;

    @Column(name = "id_turno")
    private int id_turno;

    public AdminTurnoId() {}

    public AdminTurnoId(int id_admin, int id_turno) {
        this.id_admin = id_admin;
        this.id_turno = id_turno;
    }

    // Getters y Setters

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    // Implementación de hashCode y equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminTurnoId)) return false;
        AdminTurnoId that = (AdminTurnoId) o;
        return id_admin == that.id_admin && id_turno == that.id_turno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_admin, id_turno);
    }
}
