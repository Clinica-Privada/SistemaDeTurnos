package com.cooweb.models;

import jakarta.persistence.*;

@Entity
@Table(name = "adminturno")
public class AdminTurno {
    
    @EmbeddedId
    private AdminTurnoId id;

    @ManyToOne
    @MapsId("idAdmin") // Indica que esta relación está vinculada a la clave compuesta
    @JoinColumn(name = "idAdmin", referencedColumnName = "idAdmin") // Columna en la tabla de Admin
    private Admin admin;

    @ManyToOne
    @MapsId("idTurno") // Indica que esta relación está vinculada a la clave compuesta
    @JoinColumn(name = "idTurno", referencedColumnName = "idTurno") // Columna en la tabla de Turno
    private Turno turno;

    // Constructores, getters y setters

    public AdminTurno() {}

    public AdminTurno(AdminTurnoId id, Admin admin, Turno turno) {
        this.id = id;
        this.admin = admin;
        this.turno = turno;
    }

    public AdminTurnoId getId() {
        return id;
    }

    public void setId(AdminTurnoId id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
