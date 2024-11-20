package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.dto.*;
import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.service.PersonaService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    // Agregar una nueva persona
    @PostMapping
    public ResponseEntity<PersonaDTO> agregarPersona(@RequestParam String documento, @RequestParam String nombre) throws PersonaException {
        return ResponseEntity.ok(DTOGenerator.toPersonaDTO(personaService.agregarPersona(documento, nombre)));
    }

    @GetMapping()
    public ResponseEntity<List<PersonaSimpleDTO>> getPersona() throws PersonaException {
        return ResponseEntity.ok(DTOGenerator.toPersonaSimpleDTOList(personaService.getAll()));
    }

    //Get persona by documento
    @GetMapping("/{documento}")
    public ResponseEntity<PersonaDTO> getPersonaByDocumento(@PathVariable String documento) throws PersonaException {
        Persona persona = personaService.getPersonaByDocumento(documento);
        return ResponseEntity.ok(DTOGenerator.toPersonaDTO(persona));
    }

    @GetMapping("/{documento}/reclamos")
    public ResponseEntity<List<ReclamoDTO>> getReclamosByDocumento(@PathVariable String documento) throws PersonaException {
        Persona persona = personaService.getPersonaByDocumento(documento);
        return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(persona.getReclamos()));
    }

    // Eliminar una persona
    @DeleteMapping("/{documento}")
    public ResponseEntity<String> eliminarPersona(@PathVariable String documento) throws PersonaException, FirebaseAuthException {
        personaService.eliminarPersona(documento);
        return ResponseEntity.ok("Persona Eliminado.");
    }
}
