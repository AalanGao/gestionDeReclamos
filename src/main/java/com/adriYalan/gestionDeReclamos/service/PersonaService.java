package com.adriYalan.gestionDeReclamos.service;

import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.entity.Usuario;
import com.adriYalan.gestionDeReclamos.repository.PersonaDAO;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.repository.UnidadDAO;
import com.adriYalan.gestionDeReclamos.repository.UsuarioDAO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaDAO personaDAO;
    @Autowired
    private UnidadDAO unidadDAO;
    @Autowired
    private ReclamoService reclamoService;
    @Autowired
    private UsuarioDAO usuarioDAO;

    public List<Persona> getAll() {
        return personaDAO.getAllPersonas();
    }

    public Persona agregarPersona(String documento, String nombre) throws PersonaException {
        Optional<Persona> persona = personaDAO.getPersonaByDocumento(documento);
        if (persona.isPresent()) {
            throw new PersonaException("La persona que intenta crear ya exisite.");
        }else {
            return personaDAO.guardarPersona(new Persona(documento, nombre));
        }
    }

    @Transactional
    public void eliminarPersona(String documento) throws PersonaException, FirebaseAuthException {
        //obtengo la perona y valido si existe
        Persona persona =  this.getPersonaByDocumento(documento);
        //Elimino todas las relaciones con las unidades
        this.eliminarUnidadRelacionado(persona);
        //Elimino la lista de reclamo de la persona
        reclamoService.deleteReclamoList(persona.getReclamos());
        //Elimino el usuario Sistema de la persona si tiene creedenciales
        Optional<Usuario> usuario = usuarioDAO.findByDocumento(documento);
        if (usuario.isPresent()) {
            usuarioDAO.deleteUsuario(usuario.get().getEmail());
            //Elimino el usuario en firebase
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(usuario.get().getEmail());
            FirebaseAuth.getInstance().deleteUser(userRecord.getUid());
        }
        //Elimino la persona en la tabla persona
        personaDAO.eliminarPersona(documento);
    }

    public Persona getPersonaByDocumento(String documento) throws PersonaException {
        Optional<Persona> persona = personaDAO.getPersonaByDocumento(documento);
        if (persona.isPresent()) {
            return persona.get();
        } else {
            throw new PersonaException("La persona de documento " + documento + " no existe.");
        }
    }

    private void eliminarUnidadRelacionado(Persona persona) throws PersonaException {
        List<Unidad> duenioEn = persona.getUnidadesComoDuenio();
        List<Unidad> inquilinoEn = persona.getUnidadesComoInquilino();
        List<Unidad> habitanteEn = persona.getUnidadesComoHabitante();
        for (Unidad unidad : duenioEn) {
            unidad.getDuenios().remove(persona);
            unidadDAO.actualizarUnidad(unidad);
        }
        for (Unidad unidad : inquilinoEn) {
            unidad.getInquilinos().remove(persona);
            unidadDAO.actualizarUnidad(unidad);
        }
        for (Unidad unidad : habitanteEn) {
            unidad.getHabitantes().remove(persona);
            unidadDAO.actualizarUnidad(unidad);
        }
    }
}
