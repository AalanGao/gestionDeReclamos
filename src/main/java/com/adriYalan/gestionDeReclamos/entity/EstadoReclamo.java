package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estadosreclamo")
public class EstadoReclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestado")
    private int idEstado;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    // Constructor vacío
    public EstadoReclamo() {
    }

    // Constructor con parámetros
    public EstadoReclamo(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
