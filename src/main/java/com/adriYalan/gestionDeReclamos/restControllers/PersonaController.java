package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> agregarPersona(@RequestParam String documento, @RequestParam String nombre) {
        personaService.agregarPersona(documento, nombre);
        return ResponseEntity.status(201).build(); // Devuelve un código de estado 201 (Creado)
    }

    // Eliminar una persona
    @DeleteMapping("/{documento}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable String documento) {
        try {
            personaService.eliminarPersona(documento);
            return ResponseEntity.noContent().build(); // Devuelve un código de estado 204 (Sin Contenido)
        } catch (PersonaException e) {
            return ResponseEntity.status(404).body(null); // Devuelve un código de estado 404 (No Encontrado)
        }
    }
}
