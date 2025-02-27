package com.adriYalan.gestionDeReclamos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    private String documento;
    private String nombre;
    private List<ReclamoDTO> reclamos;
    private List<UnidadSimpleDTO> duenioDe;
    private List<UnidadSimpleDTO> inquilinoEn;
    private List<UnidadSimpleDTO> habitaEn;
}
