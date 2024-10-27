package com.adriYalan.gestionDeReclamos.restControllers;
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
        public ResponseEntity<List<Edificio>> getAllEdificios() {
            return ResponseEntity.ok(edificioService.getEdificios());
        }

        @GetMapping("/{codigo}")
        public ResponseEntity<Edificio> getEdificioByCodigo(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(edificioService.getEdificiosByCodigo(codigo));
        }

        // Obtener habitantes por edificio
        @GetMapping("/{codigo}/habitantes")
        public ResponseEntity<List<Persona>> getHabitantesPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(edificioService.habilitadosPorEdificio(codigo));
        }

        // Obtener dueños por edificio
        @GetMapping("/{codigo}/duenios")
        public ResponseEntity<List<Persona>> getDueniosPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(edificioService.dueniosPorEdificio(codigo));
        }

        // Obtener inquilinos por edificio
        @GetMapping("/{codigo}/inquilinos")
        public ResponseEntity<List<Persona>> getInquilinosPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(edificioService.inquilinosPorEdificio(codigo));
        }

        // Obtener unidades por edificio
        @GetMapping("/{codigo}/unidades")
        public ResponseEntity<List<Unidad>> getUnidadesPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(edificioService.getUnidadesPorEdificio(codigo));
        }
        //Crear edificio
        @PostMapping("/crear")
        public ResponseEntity<Edificio> crearEdificio(@RequestParam String direccion, String nombre) throws EdificioException {
            return ResponseEntity.ok(edificioService.agregarEdificio(direccion,nombre));
        }

        @GetMapping("/{codigo}/reclamosZonaComun")
        public ResponseEntity<List<Reclamo>> getReclamosZonaComunPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(edificioService.getReclamoZonaComunPorEdificio(codigo));
        }

        @GetMapping("/{codigo}/reclamos")
        public ResponseEntity<List<Reclamo>> getReclamosPorEdificio(@PathVariable int codigo) throws EdificioException {
            return ResponseEntity.ok(edificioService.getReclamoPorEdificio(codigo));
        }

}


