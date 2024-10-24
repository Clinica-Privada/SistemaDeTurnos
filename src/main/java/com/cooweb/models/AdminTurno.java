package com.cooweb.models;

import jakarta.persistence.*;

@Entity
@Table(name = "adminturno")
public class AdminTurno {
    
    @EmbeddedId
    private AdminTurnoId id;

    @ManyToOne
    @MapsId("id_admin") // Indica que esta relación está vinculada a la clave compuesta
    @JoinColumn(name = "id_admin", referencedColumnName = "id_admin") // Columna en la tabla de Admin
    private Admin admin;

    @ManyToOne
    @MapsId ("id_turno")// Indica que esta relación está vinculada a la clave compuesta
    @JoinColumn(name = "id_turno", referencedColumnName = "id_turno") // Columna en la tabla de Turno
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
