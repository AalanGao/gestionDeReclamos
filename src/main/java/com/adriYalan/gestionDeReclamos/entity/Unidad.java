package com.adriYalan.gestionDeReclamos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "unidades")
public class Unidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identificador")
    private int identificador;

    @Column(nullable = false)
    private String piso;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false, length = 1)
    private String habitado;

    @ManyToOne
    @JoinColumn(name = "codigoEdificio")  // Corresponde a la FK en SQL
    private Edificio edificio;

    public Unidad() {
    }

    public Unidad(String piso, String numero, Edificio edificio) {
        this.piso = piso;
        this.numero = numero;
        this.edificio = edificio;
        this.habitado = "N";  // Por defecto no habitado
    }

    // Getters y Setters

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getHabitado() {
        return habitado;
    }

    public void setHabitado(String habitado) {
        this.habitado = habitado;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public boolean estaHabitado() {
        return "S".equalsIgnoreCase(this.habitado);
    }

    public void habitar() {
        this.habitado = "S";
    }

    public void liberar() {
        this.habitado = "N";
    }
}
