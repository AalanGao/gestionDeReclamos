package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "persona", fetch = FetchType.EAGER)
    private List<Reclamo> reclamos;

    // Constructor por defecto
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(String documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<Reclamo> reclamos) {
        this.reclamos = reclamos;
    }

    @Override
    public String toString() {

        String reclamosInfo = "";
        for (Reclamo reclamo : reclamos) {
            reclamosInfo += "Reclamo ID: " + reclamo.getIdReclamo() +
                    ", Ubicación: " + reclamo.getUbicacion() +
                    ", Descripción: " + reclamo.getDescripcion() + "; ";
        }

        return "Persona{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", reclamos=[" + reclamosInfo + "]" +
                '}';
    }
}
