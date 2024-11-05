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
    private ReclamoDAO reclamoDAO;
    @Autowired
    private UbicacionDAO ubicacionDAO;
    @Autowired
    private TipoReclamoDAO tipoReclamoDAO;
    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private UnidadDAO unidadDAO;
    @Autowired
    private EdificioDAO edificioDAO;
    @Autowired
    private EstadoReclamoDAO estadoReclamoDAO;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private EdificioService edificioService;
    @Autowired
    private UnidadService unidadService;

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

        Persona persona = personaService.getPersonaByDocumento(documento);
        Edificio edificio = edificioService.getEdificiosByCodigo(codigo);
        Unidad unidad = unidadService.getUnidad(codigo,piso,numero);
        TipoReclamo tipoReclamo = this.getTipoReclamoById(idTipTrec);
        EstadoReclamo estadoPendiente = this.getEstadoReclamoById(1);
        Reclamo reclamo = new Reclamo(persona, edificio, null, unidad, descripcion, tipoReclamo, estadoPendiente);

        reclamo.setImagenes(imagen);

        return reclamoDAO.guardarReclamo(reclamo).getIdReclamo();
    }

    public int agregarReclamo(int codigo, String documento,
                              int idUbicacion, String descripcion, int idTipTrec,
                              List<Imagen> imagen) throws EdificioException, UnidadException,
            PersonaException, ReclamoException {

        Persona persona = personaService.getPersonaByDocumento(documento);
        Edificio edificio = edificioService.getEdificiosByCodigo(codigo);
        Ubicacion ubic = this.getUbicacionById(idUbicacion);
        TipoReclamo tipoReclamo = this.getTipoReclamoById(idTipTrec);
        EstadoReclamo estadoPendiente = this.getEstadoReclamoById(1);
        Reclamo reclamo = new Reclamo(persona, edificio, ubic, null, descripcion, tipoReclamo, estadoPendiente);

        reclamo.setImagenes(imagen);

        return reclamoDAO.guardarReclamo(reclamo).getIdReclamo();
    }

    public Reclamo cambiarEstado(int idReclamo, int idEstado) throws ReclamoException {
        EstadoReclamo estado = this.getEstadoReclamoById(idEstado);
        Reclamo reclamo = getReclamoById(idReclamo);

        reclamo.setEstadoReclamo(estado);
        return reclamoDAO.guardarReclamo(reclamo);
    }

    public Reclamo getReclamoById(int idReclamo) throws ReclamoException {
        Optional<Reclamo> reclamo = reclamoDAO.getReclamoById(idReclamo);
        if (reclamo.isPresent()) {
            return reclamo.get();
        }else {
            throw new ReclamoException("El reclamo no existe.");
        }
    }


    public int contarReclamosPorEstado(int idEstado) {
        return reclamoDAO.contarReclamosPorEstado(idEstado);
    }

    public List<Reclamo> obtenerReclamosPorEstado(int idEstado) {
        return reclamoDAO.ReclamosPorEstado(idEstado);
    }

    private EstadoReclamo getEstadoReclamoById(int idEstado) {
        return estadoReclamoDAO.getEstadoReclamoById(idEstado)
                .orElseThrow(() -> new ReclamoException("El estado no existe."));
    }

    private TipoReclamo getTipoReclamoById(int idTipoReclamo) {
        return tipoReclamoDAO.getTipoReclamoById(idTipoReclamo)
                .orElseThrow(() -> new ReclamoException("El tipo de reclamo no existe."));
    }

    private Ubicacion getUbicacionById(int idUbicacion) {
        return ubicacionDAO.getUbicacionById(idUbicacion)
                .orElseThrow(() -> new UnidadException("La ubicación no existe."));
    }

    // Todo gestion de tipo reclamo, estado reclamo, ubicaciones

}
