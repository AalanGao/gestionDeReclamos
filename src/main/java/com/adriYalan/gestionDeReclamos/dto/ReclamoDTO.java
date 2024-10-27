package com.adriYalan.gestionDeReclamos.dto;

import com.adriYalan.gestionDeReclamos.entity.EstadoReclamo;
import com.adriYalan.gestionDeReclamos.entity.Imagen;
import com.adriYalan.gestionDeReclamos.entity.TipoReclamo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoDTO {
    private int idReclamo;
    private PersonaSimpleDTO persona;
    private EdificioSimpleDTO edificio;
    private UnidadSimpleDTO unidad;
    private String descripcion;
    private TipoReclamo tipoReclamo;
    private EstadoReclamo estadoReclamo;
    private LocalDateTime fechaCreacion;
    private List<Imagen> imagenes;
}