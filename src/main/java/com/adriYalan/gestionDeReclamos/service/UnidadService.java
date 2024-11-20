package com.adriYalan.gestionDeReclamos.service;

import com.adriYalan.gestionDeReclamos.entity.*;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.exception.UnidadException;
import com.adriYalan.gestionDeReclamos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadService {

    @Autowired
    private UnidadDAO unidadDAO;

    @Autowired
    private HabitanteDAO habitanteDAO;

    @Autowired
    private EdificioDAO edificioDAO;
    @Autowired
    private PersonaService personaService;

    public Unidad getUnidad(int codigo, String piso, String numero) throws UnidadException {
        Optional<Unidad> unidadOpt = unidadDAO.getUnidadByDetalles(codigo, piso, numero);
        if (unidadOpt.isEmpty()) {
            throw new UnidadException("La unidad en el edificio " + codigo + " con piso " + piso + " y número " + numero + " no existe.");
        }
        return unidadOpt.get();
    }

    public List<Persona> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        return this.getUnidad(codigo,piso,numero).getDuenios();
    }

    public List<Persona> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        return this.getUnidad(codigo,piso,numero).getInquilinos();
    }

    public List<Persona> habitantesPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        return this.getUnidad(codigo,piso,numero).getHabitantes();
    }

    public void eliminarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);
        Persona persona = personaService.getPersonaByDocumento(documento);

        boolean duenioExiste = unidad.getDuenios().stream()
                .anyMatch(duenio -> duenio.getDocumento().equals(documento));

        if (!duenioExiste) {
            throw new UnidadException("La persona no es dueño de esta Unidad");
        }

        unidad.getDuenios().remove(persona);
        unidadDAO.actualizarUnidad(unidad);
    }

    public void eliminarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);
        Persona persona = personaService.getPersonaByDocumento(documento);

        boolean inquilinoExiste = unidad.getInquilinos().stream()
                .anyMatch(duenio -> duenio.getDocumento().equals(documento));

        if (!inquilinoExiste) {
            throw new UnidadException("La persona no es inquilino de esta Unidad");
        }

        unidad.getInquilinos().remove(persona);
        unidad.actualizarHabitado();
        unidadDAO.actualizarUnidad(unidad);
    }

    public void eliminarHabitanteUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);

        Persona persona = personaService.getPersonaByDocumento(documento);

        boolean habitanteExiste = unidad.getHabitantes().stream()
                .anyMatch(habitante -> habitante.getDocumento().equals(documento));

        if (!habitanteExiste) {
            throw new UnidadException("La persona no es habitante de esta unidad.");
        }

        if (!unidad.estaHabitado()) {
            unidad.habitar();
        }

        unidad.getHabitantes().remove(persona);
        unidad.actualizarHabitado();
        unidadDAO.actualizarUnidad(unidad);
    }

    public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);

        Persona persona = personaService.getPersonaByDocumento(documento);

        boolean duenioExiste = unidad.getDuenios().stream()
                .anyMatch(duenio -> duenio.getDocumento().equals(documento));

        if (duenioExiste) {
            throw new UnidadException("La persona ya es dueño de esta unidad.");
        }

        unidad.getDuenios().add(persona);
        unidadDAO.actualizarUnidad(unidad);
    }

    public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);

        Persona persona = personaService.getPersonaByDocumento(documento);

        List<Persona> duenios = new ArrayList<>();
        duenios.add(persona);
        unidad.setDuenios(duenios);

        unidadDAO.actualizarUnidad(unidad);
    }

    public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);

        Persona persona = personaService.getPersonaByDocumento(documento);

        boolean inquilinoExiste = unidad.getInquilinos().stream()
                .anyMatch(inquilino -> inquilino.getDocumento().equals(documento));

        if (inquilinoExiste) {
            throw new UnidadException("La persona ya es inquilino en esta unidad.");
        }

        if (!unidad.estaHabitado()) {
            unidad.habitar();
        }

        unidad.getInquilinos().add(persona);
        unidadDAO.actualizarUnidad(unidad);
    }

    public void agregarHabitanteUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);

        Persona persona = personaService.getPersonaByDocumento(documento);

        boolean habitanteExiste = unidad.getHabitantes().stream()
                .anyMatch(habitante -> habitante.getDocumento().equals(documento));

        if (habitanteExiste) {
            throw new UnidadException("La persona ya es habitante de esta unidad.");
        }

        if (!unidad.estaHabitado()) {
            unidad.habitar();
        }

        unidad.getHabitantes().add(persona);
        unidadDAO.actualizarUnidad(unidad);
    }

    public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);

        int identificadorUnidad = unidad.getIdentificador();
        habitanteDAO.eliminarHabitantesPorUnidad(identificadorUnidad);
        habitanteDAO.eliminarInquilinoPorUnidad(identificadorUnidad);

        unidad.liberar();
        unidadDAO.actualizarUnidad(unidad);
    }

    public Unidad agregarUnidad(int codigo, String piso, String numero) throws UnidadException {

        Edificio edificio = edificioDAO.getEdificioByCodigo((long) codigo)
                .orElseThrow(() -> new UnidadException("El edificio no existe."));

        // Verificamos si ya existe una unidad con los mismos detalles (codigo, piso, numero)
        Optional<Unidad> unidadExistente = unidadDAO.getUnidadByDetalles(codigo, piso, numero);
        if (unidadExistente.isPresent()) {
            throw new UnidadException("La unidad ya existe en este edificio.");
        }

        // Creamos una nueva unidad
        Unidad nuevaUnidad = new Unidad(piso, numero, edificio);

        return unidadDAO.guardarUnidad(nuevaUnidad);
    }

    public void eliminarUnidad(int codigo, String piso, String numero) throws UnidadException {
        Unidad unidad = this.getUnidad(codigo,piso,numero);
        unidadDAO.eliminarUnidad(unidad.getIdentificador());
    }
}
