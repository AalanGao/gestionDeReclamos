package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tiposreclamo")
public class TipoReclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtiporeclamo")
    private int idTipoReclamo;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    public TipoReclamo() {
    }

    public TipoReclamo(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoReclamo() {
        return idTipoReclamo;
    }

    public void setIdTipoReclamo(int idTipoReclamo) {
        this.idTipoReclamo = idTipoReclamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
