package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.exception.UnidadException;
import com.adriYalan.gestionDeReclamos.service.UnidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
public class UnidadController {

    @Autowired
    private UnidadService unidadService;

    // Obtener los dueños de una unidad
    @GetMapping("/dueniosPorUnidad")
    public ResponseEntity<List<Persona>> obtenerDueniosPorUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) {
        try {
            List<Persona> duenios = unidadService.dueniosPorUnidad(codigo, piso, numero);
            return ResponseEntity.ok(duenios);
        } catch (UnidadException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Obtener los inquilinos de una unidad
    @GetMapping("/inquilinosPorUnidad")
    public ResponseEntity<List<Persona>> obtenerInquilinosPorUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) {
        try {
            List<Persona> inquilinos = unidadService.inquilinosPorUnidad(codigo, piso, numero);
            return ResponseEntity.ok(inquilinos);
        } catch (UnidadException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Obtener los habitantes de una unidad
    @GetMapping("/habitantesPorUnidad")
    public ResponseEntity<List<Persona>> obtenerHabitantesPorUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) {
        try {
            List<Persona> habitantes = unidadService.habitantesPorUnidad(codigo, piso, numero);
            return ResponseEntity.ok(habitantes);
        } catch (UnidadException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Agregar un nuevo dueño a una unidad
    @PostMapping("/agregarDuenio")
    public ResponseEntity<String> agregarDuenioUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) {
        try {
            unidadService.agregarDuenioUnidad(codigo, piso, numero, documento);
            return ResponseEntity.ok("Dueño agregado correctamente.");
        } catch (UnidadException | PersonaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Transferir la unidad a un nuevo dueño
    @PostMapping("/nuevoDuenño")
    public ResponseEntity<String> transferirUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) {
        try {
            unidadService.transferirUnidad(codigo, piso, numero, documento);
            return ResponseEntity.ok("Unidad transferida correctamente.");
        } catch (UnidadException | PersonaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Agregar un nuevo inquilino a una unidad
    @PostMapping("/agregarInquilino")
    public ResponseEntity<String> agregarInquilinoUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) {
        try {
            unidadService.agregarInquilinoUnidad(codigo, piso, numero, documento);
            return ResponseEntity.ok("Inquilino agregado correctamente.");
        } catch (UnidadException | PersonaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Agregar un nuevo habitante a una unidad
    @PostMapping("/agregarHabitante")
    public ResponseEntity<String> agregarHabitanteUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) {
        try {
            unidadService.agregarHabitanteUnidad(codigo, piso, numero, documento);
            return ResponseEntity.ok("Habitante agregado correctamente.");
        } catch (UnidadException | PersonaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Liberar una unidad de todos sus habitantes
    @DeleteMapping("liberar")
    public ResponseEntity<String> liberarUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) {
        try {
            unidadService.liberarUnidad(codigo, piso, numero);
            return ResponseEntity.ok("Unidad liberada correctamente.");
        } catch (UnidadException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Agregar una nueva unidad
    @PostMapping("/agregar")
    public ResponseEntity<Unidad> agregarUnidad(@RequestParam int codigoEdificio, @RequestParam String piso, @RequestParam String numero) {
        try {
            Unidad nuevaUnidad = unidadService.agregarUnidad(codigoEdificio, piso, numero);
            return ResponseEntity.ok(nuevaUnidad);
        } catch (UnidadException | PersonaException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
