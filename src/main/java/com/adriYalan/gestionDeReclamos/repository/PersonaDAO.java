package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonaDAO {

    private static PersonaDAO instancia;

    @Autowired
    private PersonaRepository personaRepository;

    private PersonaDAO() {}

    public static PersonaDAO getInstancia() {
        if (instancia == null) {
            instancia = new PersonaDAO();
        }
        return instancia;
    }

    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> getPersonaByDocumento(String documento) {
        return personaRepository.findById(documento);
    }

    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona actualizarPersona(Persona persona) {
        if (persona.getDocumento() != null && personaRepository.existsById(persona.getDocumento())) {
            return personaRepository.save(persona);
        }
        return null;
    }

    public void eliminarPersona(String documento) {
        personaRepository.deleteById(documento);
    }

}
