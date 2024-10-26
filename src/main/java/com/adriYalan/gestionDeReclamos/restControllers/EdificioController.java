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
        public List<Edificio> getAllEdificios() {
            return edificioService.getEdificios();
        }

        //Todo
        //getEdificio by codigo

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
        public String crearEdificio(@RequestParam String direccion, String nombre) throws EdificioException {
            edificioService.agregarEdificio(direccion,nombre);
            return "El edificio se ha agregado correctamente";
        }
        @GetMapping("/{codigo}/reclamosZonaComun")
        public List<Reclamo> getReclamosZonaComunPorEdificio(@PathVariable int codigo) throws EdificioException {
            return edificioService.getReclamoZonaComunPorEdificio(codigo);
        }

}


