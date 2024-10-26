package com.adriYalan.gestionDeReclamos.dto;

import java.util.List;

public class UnidadDTO {

    private int identificador;
    private String piso;
    private String numero;
    private String habitado;
    private EdificioDTO edificio;  // Si es necesario exponerlo
    private List<PersonaDTO> duenios;
    private List<PersonaDTO> inquilinos;
    private List<PersonaDTO> habitantes;
    private List<ReclamoDTO> reclamos;

    // Constructor, getters y setters
}

