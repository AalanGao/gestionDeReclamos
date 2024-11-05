package com.adriYalan.gestionDeReclamos.service;

import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.repository.PersonaDAO;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaDAO personaDAO;

    public Persona agregarPersona(String documento, String nombre) throws PersonaException {
        Optional<Persona> persona = personaDAO.getPersonaByDocumento(documento);
        if (persona.isPresent()) {
            throw new PersonaException("La persona que intenta crear ya exisite.");
        }else {
            return personaDAO.guardarPersona(new Persona(documento, nombre));
        }
    }

    public void eliminarPersona(String documento) throws PersonaException {
        this.getPersonaByDocumento(documento);
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
}
