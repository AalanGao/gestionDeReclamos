package com.adriYalan.gestionDeReclamos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reclamos")
public class Reclamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreclamo")
    private int idReclamo;

    @ManyToOne
    @JoinColumn(name = "documento", nullable = false)
    private Persona persona;  // Persona que realiza el reclamo

    @ManyToOne
    @JoinColumn(name = "codigo", nullable = false)
    private Edificio edificio;  // Edificio relacionado

    @ManyToOne
    @JoinColumn(name = "idubicacion", nullable = true)
    private Ubicacion ubicacion;  // Ubicación del reclamo

    @ManyToOne
    @JoinColumn(name = "identificador", nullable = true)
    private Unidad unidad;  // Unidad del reclamo (opcional)

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idtiporeclamo", nullable = false)
    private TipoReclamo tipoReclamo;

    @ManyToOne
    @JoinColumn(name = "idestado", nullable = false)
    private EstadoReclamo estadoReclamo;

    @Column(name = "fechacreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "idReclamo", fetch = FetchType.EAGER)
    private List<Imagen> imagenes;

    public Reclamo() {}

    public Reclamo(Persona persona,
                   Edificio edificio, Ubicacion ubicacion,
                   Unidad unidad, String descripcion,
                   TipoReclamo tipoReclamo, List<Imagen> imagenes) {
        this.persona = persona;
        this.edificio = edificio;
        this.ubicacion = ubicacion;
        this.unidad = unidad;
        this.descripcion = descripcion;
        this.tipoReclamo = tipoReclamo;
        this.estadoReclamo = new EstadoReclamo("Pendiente");
        this.fechaCreacion = LocalDateTime.now();
        this.imagenes = new ArrayList<>();
    }


    // Getters y setters
    public int getIdReclamo() {
        return idReclamo;
    }

    public void setIdReclamo(int idReclamo) {
        this.idReclamo = idReclamo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoReclamo getTipoReclamo() {
        return tipoReclamo;
    }

    public void setTipoReclamo(TipoReclamo tipoReclamo) {
        this.tipoReclamo = tipoReclamo;
    }

    public EstadoReclamo getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(EstadoReclamo estadoReclamo) {
        this.estadoReclamo = estadoReclamo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    @Override
    public String toString() {
        String imagenesInfo = "";
        for (Imagen imagen : imagenes) {
            imagenesInfo += "Imagen ID: " + imagen.getNumero() +
                    ", URL: " + imagen.getPath() + "; ";
        }

        return "Reclamo{" +
                "idReclamo=" + idReclamo +
                ", persona=" + (persona != null ? persona.getNombre() + " (Documento: " + persona.getDocumento() + ")" : "null") +
                ", edificio=" + (edificio != null ? edificio.getNombre() : "null") +
                ", ubicacion=" + (ubicacion != null ? ubicacion.getDescripcion() : "null") +
                ", unidad=" + (unidad != null ? unidad.getNumero() : "null") +
                ", descripcion='" + descripcion + '\'' +
                ", tipoReclamo=" + (tipoReclamo != null ? tipoReclamo.getDescripcion() : "null") +
                ", estadoReclamo=" + (estadoReclamo != null ? estadoReclamo.getDescripcion() : "null") +
                ", fechaCreacion=" + fechaCreacion +
                ", imagenes=[" + imagenesInfo + "]" +
                '}';
    }

}
