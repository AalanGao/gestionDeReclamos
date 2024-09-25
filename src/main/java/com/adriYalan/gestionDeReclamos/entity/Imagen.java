package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero")
    private int numero;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "tipo", length = 10)
    private String tipo;

    @Column(name = "idreclamo", nullable = false)
    private int idReclamo;

    public Imagen(){};

    public Imagen(int numero, String path, String tipo, int idReclamo) {
        this.numero = numero;
        this.path = path;
        this.tipo = tipo;
        this.idReclamo = idReclamo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "numero=" + numero +
                ", path='" + path + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idReclamo=" + idReclamo +
                '}';
    }
}