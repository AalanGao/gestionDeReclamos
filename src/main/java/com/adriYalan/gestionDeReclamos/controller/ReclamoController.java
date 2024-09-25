package com.adriYalan.gestionDeReclamos.controller;

import com.adriYalan.gestionDeReclamos.entity.*;
import com.adriYalan.gestionDeReclamos.exception.*;
import com.adriYalan.gestionDeReclamos.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ReclamoController {

    @Autowired
    private ReclamoDAO reclamoDAO; // DAO para manejar reclamos

    @Autowired
    private ReclamoDAO ubicacionDAO;

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

    public void agregarPersona(String documento, String nombre) {
        // 1. Crear una nueva persona
        Persona persona = new Persona();
        persona.setDocumento(documento);
        persona.setNombre(nombre);

        // 2. Guardar la persona utilizando el DAO
        personaDAO.guardarPersona(persona);
    }

    public void eliminarPersona(String documento) throws PersonaException {
        // 1. Eliminar la persona usando el DAO
        personaDAO.eliminarPersona(documento);
    }

    public List<Reclamo> reclamosPorEdificio(int codigo) {
        // 1. Obtener la lista de reclamos por edificio usando el DAO
        return EdificioDAO.getInstancia().getEdificioByCodigo((long)codigo).get().getReclamos();
    }

    public List<Reclamo> reclamosPorUnidad(int id) {
        // 1. Obtener la lista de reclamos por unidad usando el DAO
        return UnidadDAO.getInstancia().getUnidadById(id).get().getReclamos();
    }

    public Optional<Reclamo> reclamosPorNumero(int id) {
        // 1. Obtener un reclamo por su número usando el DAO
        return ReclamoDAO.getInstancia().getReclamoById(id);
    }

    /*public List<Reclamo> reclamosPorPersona(String documento) {
        // 1. Obtener la lista de reclamos por persona usando el DAO
        return  PersonaDAO.getInstancia().getPersonaByDocumento(documento).get()
    }*/

    public int agregarReclamo(int codigo, String piso, int idUnidad, String documento, int idUbicacion, String descripcion, int idTipTrec, List<Imagen> imagen)
            throws EdificioException, UnidadException, PersonaException {

        int reclamoId = -1;
        // 1. Agregar un nuevo reclamo usando el DAO
        Optional<Persona> persona = PersonaDAO.getInstancia().getPersonaByDocumento(documento);
        Optional<Edificio> edificio = EdificioDAO.getInstancia().getEdificioByCodigo((long)codigo);
        Optional<Unidad> unidad = UnidadDAO.getInstancia().getUnidadById(idUnidad);
        Optional<Ubicacion> ubic = UbicacionDAO.getInstancia().getUbicacionById(idUbicacion);
        Optional<TipoReclamo> tipoReclamo = TipoReclamoDAO.getInstancia().getTipoReclamoById(idTipTrec);
        if(persona.isPresent()&&edificio.isPresent()&&(unidad.isPresent()||ubic.isPresent())&&tipoReclamo.isPresent()) {
            Reclamo reclamo = new Reclamo(persona.get(), edificio.get(),ubic.get(),unidad.get(), descripcion, tipoReclamo.get(),imagen);
            reclamoId = ReclamoDAO.getInstancia().guardarReclamo(reclamo).getIdReclamo();
        }
        return reclamoId;
    }

   // public void agregarImagenAReclamo(int numero, String direccion, String tipo) throws ReclamoException {
        // 1. Agregar una imagen a un reclamo usando el DAO
     //   reclamoDAO.agregarImagenAReclamo(numero, direccion, tipo);
    //}

    public void cambiarEstado(int idReclamo, EstadoReclamo estado) throws ReclamoException {
        // 1. Cambiar el estado de un reclamo usando el DAO
       Optional<Reclamo> reclamo =  ReclamoDAO.getInstancia().getReclamoById(idReclamo);
       if(reclamo.isPresent()) {
           reclamo.get().setEstadoReclamo(estado);
           ReclamoDAO.getInstancia().guardarReclamo(reclamo.get());
       }
    }
}
