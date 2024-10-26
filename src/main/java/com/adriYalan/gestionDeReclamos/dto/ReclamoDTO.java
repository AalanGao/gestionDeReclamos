package com.adriYalan.gestionDeReclamos.dto;

import com.adriYalan.gestionDeReclamos.entity.EstadoReclamo;
import com.adriYalan.gestionDeReclamos.entity.Imagen;
import com.adriYalan.gestionDeReclamos.entity.TipoReclamo;

import java.time.LocalDateTime;
import java.util.List;

public class ReclamoDTO {

    private int idReclamo;
    private PersonaDTO persona;
    private EdificioDTO edificio;
    private UnidadDTO unidad;
    private String descripcion;
    private TipoReclamo tipoReclamo;
    private EstadoReclamo estadoReclamo;
    private LocalDateTime fechaCreacion;
    private List<Imagen> imagenes;

    // Constructor, getters y setters
}

