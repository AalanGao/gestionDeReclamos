package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "nombre", nullable = false)
    private String nombre;

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

    // Sobrescribir el método toString() para facilitar la depuración
    @Override
    public String toString() {
        return "Persona{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
