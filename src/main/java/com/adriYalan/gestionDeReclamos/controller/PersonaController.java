package com.adriYalan.gestionDeReclamos.controller;

import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.repository.PersonaDAO;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PersonaController {

    @Autowired
    private PersonaDAO personaDAO;

    // Método para agregar una persona
    public void agregarPersona(String documento, String nombre) {
        Persona persona = new Persona(documento, nombre);  // Crear una nueva instancia de Persona
        personaDAO.guardarPersona(persona);  // Guardar la persona utilizando el DAO
    }

    // Método para eliminar una persona
    public void eliminarPersona(String documento) throws PersonaException {
        if (personaDAO.getPersonaByDocumento(documento).isPresent()) {
            personaDAO.eliminarPersona(documento);  // Eliminar la persona si existe
        } else {
            throw new PersonaException("La persona con documento " + documento + " no existe.");  // Lanzar excepción si no existe
        }
    }
}
