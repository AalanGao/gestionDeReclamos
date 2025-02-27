package com.adriYalan.gestionDeReclamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadDTO {
    private int identificador;
    private String piso;
    private String numero;
    private String habitado;
    private EdificioSimpleDTO edificio;
    private List<PersonaSimpleDTO> duenios;
    private List<PersonaSimpleDTO> inquilinos;
    private List<PersonaSimpleDTO> habitantes;
    private List<ReclamoDTO> reclamos;
}