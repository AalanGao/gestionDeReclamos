package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "habitantes") // Cambia el nombre de la tabla a "habitantes"
public class Habitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "identificador", nullable = false)
    private int identificador;  // Referencia a la unidad

    @Column(name = "documento", nullable = false)
    private String documento;    // Referencia a la persona

    // Constructor vacío
    public Habitante() {}

    // Constructor con parámetros
    public Habitante(int identificador, String documento) {
        this.identificador = identificador;
        this.documento = documento;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}

