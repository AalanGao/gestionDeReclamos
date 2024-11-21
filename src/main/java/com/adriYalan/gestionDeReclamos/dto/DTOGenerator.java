package com.adriYalan.gestionDeReclamos.dto;

import com.adriYalan.gestionDeReclamos.entity.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DTOGenerator {
    public static PersonaDTO toPersonaDTO(Persona persona) {
        return new PersonaDTO(
                persona.getDocumento(),
                persona.getNombre(),
                persona.getReclamos() != null ?
                        persona.getReclamos().stream()
                                .map(DTOGenerator::toReclamoDTO)
                                .collect(Collectors.toList())
                        : Collections.emptyList(),
                persona.getUnidadesComoDuenio() != null ?
                        persona.getUnidadesComoDuenio().stream()
                                .map(DTOGenerator::toUnidadSimpleDTO)
                                .collect(Collectors.toList())
                        : Collections.emptyList(),
                persona.getUnidadesComoInquilino() != null ?
                        persona.getUnidadesComoInquilino().stream()
                                .map(DTOGenerator::toUnidadSimpleDTO)
                                .collect(Collectors.toList())
                        : Collections.emptyList(),
                persona.getUnidadesComoHabitante() != null ?
                        persona.getUnidadesComoHabitante().stream()
                                .map(DTOGenerator::toUnidadSimpleDTO)
                                .collect(Collectors.toList())
                        : Collections.emptyList()
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
        if (unidad != null) {
            return new UnidadSimpleDTO(unidad.getIdentificador(), unidad.getPiso(), unidad.getNumero(), unidad.getHabitado(), unidad.getEdificio().getCodigo().toString());
        }
        return null;
    }

    // Reclamo
    public static ReclamoDTO toReclamoDTO(Reclamo reclamo) {
        return new ReclamoDTO(
                reclamo.getIdReclamo(),
                toPersonaSimpleDTO(reclamo.getPersona()),
                reclamo.getUnidad() != null ? toEdificioSimpleDTO(reclamo.getUnidad().getEdificio()) : null,
                reclamo.getUnidad() != null ? toUnidadSimpleDTO(reclamo.getUnidad()) : null,
                reclamo.getDescripcion(),
                reclamo.getTipoReclamo(),
                reclamo.getEstadoReclamo(),
                reclamo.getFechaCreacion(),
                reclamo.getUbicacion() != null ? reclamo.getUbicacion() : null,
                reclamo.getImagenes() != null ? reclamo.getImagenes() : Collections.emptyList()
        );
    }


    public static List<ReclamoDTO> toReclamoDTOList(List<Reclamo> reclamos) {
        return reclamos.stream()
                .map(DTOGenerator::toReclamoDTO)
                .collect(Collectors.toList());

    }

    public static List<EdificioSimpleDTO> toEdificioSimpleDTOList(List<Edificio> edificios) {
        return edificios.stream()
                .map(DTOGenerator::toEdificioSimpleDTO)
                .collect(Collectors.toList());
    }

    public static List<PersonaSimpleDTO> toPersonaSimpleDTOList(List<Persona> personas) {
        return personas.stream()
                .map(DTOGenerator::toPersonaSimpleDTO)
                .collect(Collectors.toList());
    }

    public static List<UnidadSimpleDTO> toUnidadSimpleDTOList(List<Unidad> unidades) {
        return unidades.stream()
                .map(DTOGenerator::toUnidadSimpleDTO)
                .collect(Collectors.toList());
    }

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getEmail(), usuario.getPassword(), usuario.getDocumento(), usuario.getRol());
    }
}
