package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.dto.*;
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

    @GetMapping()
    public ResponseEntity<UnidadDTO> obtenerUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) {
        Unidad unidad = unidadService.getUnidad(codigo, piso, numero);
        return ResponseEntity.ok(DTOGenerator.toUnidadDTO(unidad));
    }

    // Obtener los dueños de una unidad
    @GetMapping("/dueniosPorUnidad")
    public ResponseEntity<List<PersonaSimpleDTO>> obtenerDueniosPorUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) {
        List<Persona> duenios = unidadService.dueniosPorUnidad(codigo, piso, numero);
        return ResponseEntity.ok(DTOGenerator.toPersonaSimpleDTOList(duenios));
    }

    // Obtener los inquilinos de una unidad
    @GetMapping("/inquilinosPorUnidad")
    public ResponseEntity<List<PersonaSimpleDTO>> obtenerInquilinosPorUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) {
        List<Persona> inquilinos = unidadService.inquilinosPorUnidad(codigo, piso, numero);
        return ResponseEntity.ok(DTOGenerator.toPersonaSimpleDTOList(inquilinos));
    }

    // Obtener los habitantes de una unidad
    @GetMapping("/habitantesPorUnidad")
    public ResponseEntity<List<PersonaSimpleDTO>> obtenerHabitantesPorUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) {
        List<Persona> habitantes = unidadService.habitantesPorUnidad(codigo, piso, numero);
        return ResponseEntity.ok(DTOGenerator.toPersonaSimpleDTOList(habitantes));
    }

    // Agregar un nuevo dueño a una unidad
    @PostMapping("/agregarDuenio")
    public ResponseEntity<String> agregarDuenioUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException {
            unidadService.agregarDuenioUnidad(codigo, piso, numero, documento);
            return ResponseEntity.ok("Dueño agregado correctamente.");
    }

    // Transferir la unidad a un nuevo dueño
    @PostMapping("/nuevoDuenio")
    public ResponseEntity<String> transferirUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException {
        unidadService.transferirUnidad(codigo, piso, numero, documento);
        return ResponseEntity.ok("Unidad transferida correctamente.");
    }

    // Agregar un nuevo inquilino a una unidad
    @PostMapping("/agregarInquilino")
    public ResponseEntity<String> agregarInquilinoUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException {
        unidadService.agregarInquilinoUnidad(codigo, piso, numero, documento);
        return ResponseEntity.ok("Inquilino agregado correctamente.");
    }

    // Agregar un nuevo habitante a una unidad
    @PostMapping("/agregarHabitante")
    public ResponseEntity<String> agregarHabitanteUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException {
        unidadService.agregarHabitanteUnidad(codigo, piso, numero, documento);
        return ResponseEntity.ok("Habitante agregado correctamente.");
    }

    @DeleteMapping("/eliminarHabitante")
    public ResponseEntity<String> eliminarHabitanteUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException {
        unidadService.eliminarHabitanteUnidad(codigo, piso, numero, documento);
        return ResponseEntity.ok("Habitante Eliminado correctamente.");
    }

    @DeleteMapping("/eliminarInquilino")
    public ResponseEntity<String> eliminarInquilinoUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException {
        unidadService.eliminarInquilinoUnidad(codigo, piso, numero, documento);
        return ResponseEntity.ok("Inquilino Eliminado correctamente.");
    }

    @DeleteMapping("/eliminarDuenio")
    public ResponseEntity<String> eliminarDuenioUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException {
        unidadService.eliminarDuenioUnidad(codigo, piso, numero, documento);
        return ResponseEntity.ok("Duenio Eliminado correctamente.");
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarDuenioUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) throws PersonaException {
        unidadService.eliminarUnidad(codigo, piso, numero);
        return ResponseEntity.ok("Unidad Eliminado correctamente.");
    }

    // Liberar una unidad de todos sus habitantes
    @DeleteMapping("liberar")
    public ResponseEntity<String> liberarUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) throws UnidadException{
        unidadService.liberarUnidad(codigo, piso, numero);
        return ResponseEntity.ok("Unidad liberada correctamente.");
    }

    // Agregar una nueva unidad
    @PostMapping("/agregar")
    public ResponseEntity<UnidadDTO> agregarUnidad(@RequestParam int codigo, @RequestParam String piso, @RequestParam String numero) throws UnidadException{
        Unidad nuevaUnidad = unidadService.agregarUnidad(codigo, piso, numero);
        return ResponseEntity.ok(DTOGenerator.toUnidadDTO(nuevaUnidad));
    }
}
