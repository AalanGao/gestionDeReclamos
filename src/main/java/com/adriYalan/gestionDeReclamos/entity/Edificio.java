package com.adriYalan.gestionDeReclamos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Unidad> unidades;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "duenios",
            joinColumns = @JoinColumn(name = "identificador"),  // Columna de la tabla unidades
            inverseJoinColumns = @JoinColumn(name = "documento")  // Columna de la tabla personas
    )
    @JsonIgnore
    private List<Persona> duenios;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "inquilinos",
            joinColumns = @JoinColumn(name = "identificador"),  // Columna de la tabla unidades
            inverseJoinColumns = @JoinColumn(name = "documento")  // Columna de la tabla personas
    )
    @JsonIgnore
    private List<Persona> inquilinos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "habitantes",
            joinColumns = @JoinColumn(name = "identificador"),  // Columna de la tabla unidades
            inverseJoinColumns = @JoinColumn(name = "documento")  // Columna de la tabla personas
    )
    @JsonIgnore
    private List<Persona> habitantes;

    @OneToMany(mappedBy = "edificio", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Reclamo> reclamos;

    public Edificio() {
    }

    public Edificio(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.unidades = new ArrayList<>();
        this.reclamos = new ArrayList<>();
        this.duenios = new ArrayList<>();
        this.inquilinos = new ArrayList<>();
        this.habitantes = new ArrayList<>();
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

    public List<Persona> getDuenios() {
        return duenios;
    }

    public void setDuenios(List<Persona> duenios) {
        this.duenios = duenios;
    }

    public List<Persona> getInquilinos() {
        return inquilinos;
    }

    public void setInquilinos(List<Persona> inquilinos) {
        this.inquilinos = inquilinos;
    }

    public List<Persona> getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(List<Persona> habitantes) {
        this.habitantes = habitantes;
    }

    @Override
    public String toString() {
        String unidadesInfo = "";
        for (Unidad unidad : unidades) {
            unidadesInfo += "Unidad ID: " + unidad.getIdentificador() +
                    ", Piso: " + unidad.getPiso() +
                    ", Número: " + unidad.getNumero() + "; ";
        }

        String reclamosInfo = "";
        for (Reclamo reclamo : reclamos) {
            reclamosInfo += "Reclamo ID: " + reclamo.getIdReclamo() +
                    ", Ubicación: " + reclamo.getUbicacion() +
                    ", Descripción: " + reclamo.getDescripcion() + "; ";
        }

        return "Edificio{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", unidades=[" + unidadesInfo + "]" +
                ", reclamos=[" + reclamosInfo + "]" +
                '}';
    }
}
