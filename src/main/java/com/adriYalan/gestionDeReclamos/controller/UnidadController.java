package com.adriYalan.gestionDeReclamos.controller;

import com.adriYalan.gestionDeReclamos.entity.*;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.exception.UnidadException;
import com.adriYalan.gestionDeReclamos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UnidadController {

    @Autowired
    private UnidadDAO unidadDAO;

    @Autowired
    private PersonaDAO personaDAO;

    @Autowired
    private DuenioDAO duenioDAO;

    @Autowired
    private InquilinoDAO inquilinoDAO;

    @Autowired
    private HabitanteDAO habitanteDAO;

    public List<Persona> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        Optional<Unidad> unidadOpt = unidadDAO.getUnidadByDetalles(codigo, piso, numero);
        if (unidadOpt.isEmpty()) {
            throw new UnidadException("La unidad en el edificio " + codigo + " con piso " + piso + " y número " + numero + " no existe.");
        }
        return unidadOpt.get().getDuenios();
    }

    public List<Persona> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        Optional<Unidad> unidadOpt = unidadDAO.getUnidadByDetalles(codigo, piso, numero);
        if (unidadOpt.isEmpty()) {
            throw new UnidadException("La unidad en el edificio " + codigo + " con piso " + piso + " y número " + numero + " no existe.");
        }
        return unidadOpt.get().getInquilinos();
    }

    public List<Persona> habitantesPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        Optional<Unidad> unidadOpt = unidadDAO.getUnidadByDetalles(codigo, piso, numero);
        if (unidadOpt.isEmpty()) {
            throw new UnidadException("La unidad en el edificio " + codigo + " con piso " + piso + " y número " + numero + " no existe.");
        }
        return unidadOpt.get().getHabitantes();
    }

    public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = unidadDAO.getUnidadByDetalles(codigo, piso, numero)
                .orElseThrow(() -> new UnidadException("La unidad no existe."));

        Persona persona = personaDAO.getPersonaByDocumento(documento)
                .orElseThrow(() -> new PersonaException("La persona no existe."));

        boolean duenioExiste = unidad.getDuenios().stream()
                .anyMatch(duenio -> duenio.getDocumento().equals(documento));

        if (duenioExiste) {
            throw new UnidadException("La persona ya es dueña de esta unidad.");
        }

        Duenio nuevoDuenio = new Duenio();
        nuevoDuenio.setDocumento(documento);
        nuevoDuenio.setIdentificador(unidad.getIdentificador());

        duenioDAO.guardarDuenio(nuevoDuenio);
        unidad.getDuenios().add(persona);
        unidadDAO.actualizarUnidad(unidad);
    }

    public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = unidadDAO.getUnidadByDetalles(codigo, piso, numero)
                .orElseThrow(() -> new UnidadException("La unidad no existe."));

        Persona persona = personaDAO.getPersonaByDocumento(documento)
                .orElseThrow(() -> new PersonaException("La persona no existe."));

        List<Persona> duenios = new ArrayList<>();
        duenios.add(persona);
        unidad.setDuenios(duenios);

        unidadDAO.actualizarUnidad(unidad);
    }

    public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = unidadDAO.getUnidadByDetalles(codigo, piso, numero)
                .orElseThrow(() -> new UnidadException("La unidad no existe."));

        Persona persona = personaDAO.getPersonaByDocumento(documento)
                .orElseThrow(() -> new PersonaException("La persona no existe."));

        boolean inquilinoExiste = unidad.getInquilinos().stream()
                .anyMatch(inquilino -> inquilino.getDocumento().equals(documento));

        if (inquilinoExiste) {
            throw new UnidadException("La persona ya es inquilino en esta unidad.");
        }

        Inquilino nuevoInquilino = new Inquilino();
        nuevoInquilino.setDocumento(documento);
        nuevoInquilino.setIdentificador(unidad.getIdentificador());

        inquilinoDAO.guardarInquilino(nuevoInquilino);
        unidad.getInquilinos().add(persona);
        unidadDAO.actualizarUnidad(unidad);
    }

    public void agregarHabitanteUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = unidadDAO.getUnidadByDetalles(codigo, piso, numero)
                .orElseThrow(() -> new UnidadException("La unidad no existe."));

        Persona persona = personaDAO.getPersonaByDocumento(documento)
                .orElseThrow(() -> new PersonaException("La persona no existe."));

        boolean habitanteExiste = unidad.getHabitantes().stream()
                .anyMatch(habitante -> habitante.getDocumento().equals(documento));

        if (habitanteExiste) {
            throw new UnidadException("La persona ya es habitante de esta unidad.");
        }

        Habitante nuevoHabitante = new Habitante();
        nuevoHabitante.setDocumento(documento);
        nuevoHabitante.setIdentificador(unidad.getIdentificador());

        habitanteDAO.guardarHabitante(nuevoHabitante);

        if (!unidad.estaHabitado()) {
            unidad.habitar();
        }

        unidad.getHabitantes().add(persona);
        unidadDAO.actualizarUnidad(unidad);
    }

    @Transactional
    public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
        Unidad unidad = unidadDAO.getUnidadByDetalles(codigo, piso, numero)
                .orElseThrow(() -> new UnidadException("La unidad no existe."));

        int identificadorUnidad = unidad.getIdentificador();
        habitanteDAO.eliminarHabitantesPorUnidad(identificadorUnidad);

        unidad.liberar();
        unidadDAO.actualizarUnidad(unidad);
    }
}
