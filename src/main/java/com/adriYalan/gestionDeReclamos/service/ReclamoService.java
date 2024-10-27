package com.adriYalan.gestionDeReclamos.service;

import com.adriYalan.gestionDeReclamos.entity.*;
import com.adriYalan.gestionDeReclamos.exception.*;
import com.adriYalan.gestionDeReclamos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamoService {

    @Autowired
    private ReclamoDAO reclamoDAO; // DAO para manejar reclamos

    @Autowired
    private UbicacionDAO ubicacionDAO; // DAO para manejar ubicaciones

    @Autowired
    private TipoReclamoDAO tipoReclamoDAO;

    @Autowired
    private PersonaDAO personaDAO; // DAO para manejar personas

    @Autowired
    private UnidadDAO unidadDAO; // DAO para manejar unidades

    @Autowired
    private EdificioDAO edificioDAO; // DAO para manejar edificios

    @Autowired
    private EstadoReclamoDAO estadoReclamoDAO; // DAO para manejar estados de reclamos

    public List<Reclamo> reclamosPorEdificio(int codigo) {
        return edificioDAO.getEdificioByCodigo((long)codigo).get().getReclamos();
    }

    public List<Reclamo> reclamosPorUnidad(int id) {
        return unidadDAO.getUnidadById(id).get().getReclamos();
    }

    public Reclamo reclamosPorNumero(int id) {
        return reclamoDAO.getReclamoById(id).get();
    }

    public List<Reclamo> reclamosPorPersona(String documento) {
        return personaDAO.getPersonaByDocumento(documento).get().getReclamos();
    }

    public int agregarReclamo(int codigo, String piso, String numero, String documento,
                              String descripcion, int idTipTrec,
                              List<Imagen> imagen) throws EdificioException, UnidadException,
            PersonaException, ReclamoException {

        Persona persona = personaDAO.getPersonaByDocumento(documento)
                .orElseThrow(() -> new PersonaException("La persona no existe."));

        Edificio edificio = edificioDAO.getEdificioByCodigo((long)codigo)
                .orElseThrow(() -> new EdificioException("El edificio no existe."));


        Unidad unidad = unidadDAO.getUnidadByDetalles(codigo, piso, numero)
                .orElseThrow(() -> new UnidadException("La unidad no existe."));

        TipoReclamo tipoReclamo = tipoReclamoDAO.getTipoReclamoById(idTipTrec)
                .orElseThrow(() -> new ReclamoException("El tipo de reclamo no existe."));

        EstadoReclamo estadoPendiente = estadoReclamoDAO.getEstadoReclamoById(1)
                .orElseThrow(() -> new ReclamoException("El tipo de reclamo no existe."));

        Reclamo reclamo = new Reclamo(persona, edificio, null, unidad, descripcion, tipoReclamo, estadoPendiente);

        return reclamoDAO.guardarReclamo(reclamo).getIdReclamo();
    }

    public int agregarReclamo(int codigo, String documento,
                              int idUbicacion, String descripcion, int idTipTrec,
                              List<Imagen> imagen) throws EdificioException, UnidadException,
            PersonaException, ReclamoException {

        Persona persona = personaDAO.getPersonaByDocumento(documento)
                .orElseThrow(() -> new PersonaException("La persona no existe."));

        Edificio edificio = edificioDAO.getEdificioByCodigo((long)codigo)
                .orElseThrow(() -> new EdificioException("El edificio no existe."));

        Ubicacion ubic = ubicacionDAO.getUbicacionById(idUbicacion)
                .orElseThrow(() -> new UnidadException("La ubicación no existe."));

        TipoReclamo tipoReclamo = tipoReclamoDAO.getTipoReclamoById(idTipTrec)
                .orElseThrow(() -> new ReclamoException("El tipo de reclamo no existe."));

        EstadoReclamo estadoPendiente = estadoReclamoDAO.getEstadoReclamoById(1)
                .orElseThrow(() -> new ReclamoException("El tipo de reclamo no existe."));

        Reclamo reclamo = new Reclamo(persona, edificio, ubic, null, descripcion, tipoReclamo, estadoPendiente);

        reclamo.setImagenes(imagen);

        return reclamoDAO.guardarReclamo(reclamo).getIdReclamo();
    }

    public void cambiarEstado(int idReclamo, EstadoReclamo estado) throws ReclamoException {
        Optional<Reclamo> reclamo = reclamoDAO.getReclamoById(idReclamo);
        if(reclamo.isPresent()) {
            reclamo.get().setEstadoReclamo(estado);
            reclamoDAO.guardarReclamo(reclamo.get());
        } else {
            throw new ReclamoException("El reclamo no existe.");
        }
    }

    public int contarReclamosPorEstado(int idEstado) {
        return reclamoDAO.contarReclamosPorEstado(idEstado);
    }

    public List<Reclamo> obtenerReclamosPorEstado(int idEstado) {
        return reclamoDAO.ReclamosPorEstado(idEstado);
    }

    // Todo gestion de tipo reclamo, estado reclamo, ubicaciones

}
