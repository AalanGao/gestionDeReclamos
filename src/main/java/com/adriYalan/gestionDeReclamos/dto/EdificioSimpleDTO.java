package com.adriYalan.gestionDeReclamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EdificioSimpleDTO {
    private Long codigo;
    private String nombre;
    private String direccion;
}
