package com.adriYalan.gestionDeReclamos.restControllers;
import com.adriYalan.gestionDeReclamos.dto.*;
import com.adriYalan.gestionDeReclamos.entity.Edificio;
import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.exception.EdificioException;
import com.adriYalan.gestionDeReclamos.repository.EdificioDAO;
import com.adriYalan.gestionDeReclamos.service.EdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/edificios")

public class EdificioController {

        private final EdificioService edificioService;

        @Autowired
        public EdificioController(EdificioService edificioService) {
            this.edificioService = edificioService;
        }

        // Obtener todos los edificios
        @GetMapping
        public ResponseEntity<List<EdificioSimpleDTO>> getAllEdificios() {
            return ResponseEntity.ok(DTOGenerator.toEdificioSimpleDTOList(edificioService.getEdificios()));
        }

        @GetMapping("/{codigo}")
        public ResponseEntity<EdificioDTO> getEdificioByCodigo(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(DTOGenerator.toEdificioDTO(edificioService.getEdificiosByCodigo(codigo)));
        }

        // Obtener habitantes por edificio
        @GetMapping("/{codigo}/habitantes")
        public ResponseEntity<List<PersonaSimpleDTO>> getHabitantesPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(DTOGenerator.toPersonaSimpleDTOList(edificioService.habilitadosPorEdificio(codigo)));
        }

        // Obtener dueños por edificio
        @GetMapping("/{codigo}/duenios")
        public ResponseEntity<List<PersonaSimpleDTO>> getDueniosPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(DTOGenerator.toPersonaSimpleDTOList(edificioService.dueniosPorEdificio(codigo)));
        }

        // Obtener inquilinos por edificio
        @GetMapping("/{codigo}/inquilinos")
        public ResponseEntity<List<PersonaSimpleDTO>> getInquilinosPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(DTOGenerator.toPersonaSimpleDTOList(edificioService.inquilinosPorEdificio(codigo)));
        }

        // Obtener unidades por edificio
        @GetMapping("/{codigo}/unidades")
        public ResponseEntity<List<UnidadSimpleDTO>> getUnidadesPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(DTOGenerator.toUnidadSimpleDTOList(edificioService.getUnidadesPorEdificio(codigo)));
        }
        //Crear edificio
        @PostMapping("/crear")
        public ResponseEntity<EdificioDTO> crearEdificio(@RequestParam String direccion, String nombre) throws EdificioException {
            return ResponseEntity.ok(DTOGenerator.toEdificioDTO(edificioService.agregarEdificio(direccion,nombre)));
        }

        @GetMapping("/{codigo}/reclamosZonaComun")
        public ResponseEntity<List<ReclamoDTO>> getReclamosZonaComunPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(edificioService.getReclamoZonaComunPorEdificio(codigo)));
        }

        @GetMapping("/{codigo}/reclamos")
        public ResponseEntity<List<ReclamoDTO>> getReclamosPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(edificioService.getReclamoPorEdificio(codigo)));
        }

}


