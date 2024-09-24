package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "edificios")
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")  // Mapeo de la columna primaria
    private Long codigo;

    private String nombre;

    private String direccion;

    @OneToMany(mappedBy = "edificio", fetch = FetchType.EAGER)
    private List<Unidad> unidades;

    @OneToMany(mappedBy = "edificio", fetch = FetchType.EAGER)
    private List<Reclamo> reclamos;

    public Edificio() {
    }

    public Edificio(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.unidades = new ArrayList<>();
        this.reclamos = new ArrayList<>();
    }

    // Getters y Setters

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public void setReclamos(List<Reclamo> reclamos) {
        this.reclamos = reclamos;
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", unidades=" + unidades +
                ", reclamos=" + reclamos +
                '}';
    }
}
