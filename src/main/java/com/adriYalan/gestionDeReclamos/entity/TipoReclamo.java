package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tiposReclamo")
public class TipoReclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoReclamo")
    private int idTipoReclamo;

    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;

    // Constructor vacío
    public TipoReclamo() {
    }

    // Constructor con parámetros
    public TipoReclamo(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
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
