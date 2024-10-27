package com.adriYalan.gestionDeReclamos.dto;

import com.adriYalan.gestionDeReclamos.entity.Edificio;
import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.entity.Unidad;

import java.util.List;
import java.util.stream.Collectors;

public class DTOGenerator {
    public static PersonaDTO toPersonaDTO(Persona persona) {
        return new PersonaDTO(
                persona.getDocumento(),
                persona.getNombre(),
                persona.getReclamos().stream()
                        .map(DTOGenerator::toReclamoDTO)
                        .collect(Collectors.toList()),
                persona.getUnidadesComoDuenio().stream()
                        .map(DTOGenerator::toUnidadSimpleDTO)
                        .collect(Collectors.toList()),
                persona.getUnidadesComoInquilino().stream()
                        .map(DTOGenerator::toUnidadSimpleDTO)
                        .collect(Collectors.toList()),
                persona.getUnidadesComoHabitante().stream()
                        .map(DTOGenerator::toUnidadSimpleDTO)
                        .collect(Collectors.toList())
        );
    }

    public static PersonaSimpleDTO toPersonaSimpleDTO(Persona persona) {
        return new PersonaSimpleDTO(persona.getDocumento(), persona.getNombre());
    }

    // Edificio
    public static EdificioDTO toEdificioDTO(Edificio edificio) {
        return new EdificioDTO(
                edificio.getCodigo(),
                edificio.getNombre(),
                edificio.getDireccion(),
                edificio.getUnidades().stream()
                        .map(DTOGenerator::toUnidadSimpleDTO)
                        .collect(Collectors.toList()),
                edificio.getDuenios().stream()
                        .map(DTOGenerator::toPersonaSimpleDTO)
                        .collect(Collectors.toList()),
                edificio.getInquilinos().stream()
                        .map(DTOGenerator::toPersonaSimpleDTO)
                        .collect(Collectors.toList()),
                edificio.getHabitantes().stream()
                        .map(DTOGenerator::toPersonaSimpleDTO)
                        .collect(Collectors.toList())
        );
    }

    public static EdificioSimpleDTO toEdificioSimpleDTO(Edificio edificio) {
        return new EdificioSimpleDTO(edificio.getCodigo(), edificio.getNombre(), edificio.getDireccion());
    }

    // Unidad
    public static UnidadDTO toUnidadDTO(Unidad unidad) {
        return new UnidadDTO(
                unidad.getIdentificador(),
                unidad.getPiso(),
                unidad.getNumero(),
                unidad.getHabitado(),
                toEdificioSimpleDTO(unidad.getEdificio()),
                unidad.getDuenios().stream()
                        .map(DTOGenerator::toPersonaSimpleDTO)
                        .collect(Collectors.toList()),
                unidad.getInquilinos().stream()
                        .map(DTOGenerator::toPersonaSimpleDTO)
                        .collect(Collectors.toList()),
                unidad.getHabitantes().stream()
                        .map(DTOGenerator::toPersonaSimpleDTO)
                        .collect(Collectors.toList()),
                unidad.getReclamos().stream()
                        .map(DTOGenerator::toReclamoDTO)
                        .collect(Collectors.toList())
        );
    }

    public static UnidadSimpleDTO toUnidadSimpleDTO(Unidad unidad) {
        return new UnidadSimpleDTO(unidad.getIdentificador(), unidad.getPiso(), unidad.getNumero(), unidad.getHabitado());
    }

    // Reclamo
    public static ReclamoDTO toReclamoDTO(Reclamo reclamo) {
        return new ReclamoDTO(
                reclamo.getIdReclamo(),
                toPersonaSimpleDTO(reclamo.getPersona()),
                toEdificioSimpleDTO(reclamo.getUnidad().getEdificio()),
                toUnidadSimpleDTO(reclamo.getUnidad()),
                reclamo.getDescripcion(),
                reclamo.getTipoReclamo(),
                reclamo.getEstadoReclamo(),
                reclamo.getFechaCreacion(),
                reclamo.getImagenes()
        );
    }

    public static List<ReclamoDTO> toReclamoDTOList(List<Reclamo> reclamos) {
        return reclamos.stream()
                .map(DTOGenerator::toReclamoDTO)
                .collect(Collectors.toList());

    }
}
