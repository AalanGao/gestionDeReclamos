package com.adriYalan.gestionDeReclamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadSimpleDTO {
    private int identificador;
    private String piso;
    private String numero;
    private String habitado;
}
