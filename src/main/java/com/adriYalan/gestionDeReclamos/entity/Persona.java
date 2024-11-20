package com.adriYalan.gestionDeReclamos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "personas")
public class Persona {

    @Setter
    @Id
    @Column(name = "documento", nullable = false)
    private String documento;

    @Setter
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Setter
    @OneToMany(mappedBy = "persona", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Reclamo> reclamos;

    // Lista de unidades donde es dueño
    @ManyToMany(mappedBy = "duenios", fetch = FetchType.LAZY)
    private List<Unidad> unidadesComoDuenio;

    // Lista de unidades donde es inquilino
    @ManyToMany(mappedBy = "inquilinos", fetch = FetchType.LAZY)
    private List<Unidad> unidadesComoInquilino;

    // Lista de unidades donde es habitante
    @ManyToMany(mappedBy = "habitantes", fetch = FetchType.LAZY)
    private List<Unidad> unidadesComoHabitante;

    public Persona() {
    }

    public Persona(String documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Reclamo> getReclamos() {
        return reclamos;
    }

    public List<Unidad> getUnidadesComoDuenio() {
        return unidadesComoDuenio;
    }

    public List<Unidad> getUnidadesComoInquilino() {
        return unidadesComoInquilino;
    }

    public List<Unidad> getUnidadesComoHabitante() {
        return unidadesComoHabitante;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", reclamos=" + reclamos +
                ", unidadesComoDuenio=" + unidadesComoDuenio +
                ", unidadesComoInquilino=" + unidadesComoInquilino +
                ", unidadesComoHabitante=" + unidadesComoHabitante +
                '}';
    }
}
