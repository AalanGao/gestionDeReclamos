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

    // Constructor privado para el patrón singleton
    private PersonaDAO() {}

    // Método para obtener la instancia del DAO
    public static PersonaDAO getInstancia() {
        if (instancia == null) {
            instancia = new PersonaDAO();
        }
        return instancia;
    }

    // Método para obtener todas las personas
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    // Método para obtener una persona por su documento
    public Optional<Persona> getPersonaByDocumento(String documento) {
        return personaRepository.findById(documento);
    }

    // Método para guardar una nueva persona
    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    // Método para actualizar una persona
    public Persona actualizarPersona(Persona persona) {
        if (persona.getDocumento() != null && personaRepository.existsById(persona.getDocumento())) {
            return personaRepository.save(persona);
        }
        return null;
    }

    // Método para eliminar una persona por su documento
    public void eliminarPersona(String documento) {
        personaRepository.deleteById(documento);
    }

    // Puedes agregar métodos personalizados según tus necesidades
}
