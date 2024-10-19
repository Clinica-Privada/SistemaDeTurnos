package com.cooweb.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AdminTurnoId implements Serializable {
    @Column(name = "idAdmin")
    private int idAdmin;

    @Column(name = "idTurno")
    private int idTurno;

    public AdminTurnoId() {}

    public AdminTurnoId(int idAdmin, int idTurno) {
        this.idAdmin = idAdmin;
        this.idTurno = idTurno;
    }

    // Getters y Setters

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    // Implementación de hashCode y equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdminTurnoId)) return false;
        AdminTurnoId that = (AdminTurnoId) o;
        return idAdmin == that.idAdmin && idTurno == that.idTurno;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdmin, idTurno);
    }
}
