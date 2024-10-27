package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.entity.EstadoReclamo;
import com.adriYalan.gestionDeReclamos.exception.EdificioException;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.exception.ReclamoException;
import com.adriYalan.gestionDeReclamos.service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {

    @Autowired
    private ReclamoService reclamoService;

    // Obtener reclamos por código de edificio
    @GetMapping("/edificio/{codigo}")
    public ResponseEntity<List<Reclamo>> obtenerReclamosPorEdificio(@PathVariable int codigo) {
        List<Reclamo> reclamos = reclamoService.reclamosPorEdificio(codigo);
        return ResponseEntity.ok(reclamos);
    }

    // Obtener reclamos por unidad
    @GetMapping("/unidad/{id}")
    public ResponseEntity<List<Reclamo>> obtenerReclamosPorUnidad(@PathVariable int id) {
        List<Reclamo> reclamos = reclamoService.reclamosPorUnidad(id);
        return ResponseEntity.ok(reclamos);
    }

    // Obtener reclamo por número de reclamo
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reclamo>> obtenerReclamoPorNumero(@PathVariable int id) {
        Optional<Reclamo> reclamo = reclamoService.reclamosPorNumero(id);
        return ResponseEntity.ok(reclamo);
    }

    // Obtener reclamos por documento de persona
    @GetMapping("/persona/{documento}")
    public ResponseEntity<List<Reclamo>> obtenerReclamosPorPersona(@PathVariable String documento) {
        List<Reclamo> reclamos = reclamoService.reclamosPorPersona(documento);
        return ResponseEntity.ok(reclamos);
    }

    // Agregar un nuevo reclamo
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarReclamo(
            @RequestParam int codigo,
            @RequestParam String piso,
            @RequestParam String numero,
            @RequestParam String documento,
            @RequestParam String descripcion,
            @RequestParam int idTipTrec
    ) throws PersonaException, EdificioException {
        // veo Firebase imagen
        int idReclamo = reclamoService.agregarReclamo(codigo, piso, numero, documento, descripcion, idTipTrec, null);
        return ResponseEntity.ok("Reclamo agregado con ID: " + idReclamo);
    }

    @PostMapping("/agregarZonaComun")
    public ResponseEntity<String> agregarReclamo(
            @RequestParam int codigo,
            @RequestParam String documento,
            @RequestParam int idUbicacion,
            @RequestParam String descripcion,
            @RequestParam int idTipTrec
    ) throws PersonaException, EdificioException {
        // veo Firebase imagen
        int idReclamo = reclamoService.agregarReclamo(codigo, documento, idUbicacion, descripcion, idTipTrec, null);
        return ResponseEntity.ok("Reclamo agregado con ID: " + idReclamo);
    }

    // Cambiar estado de un reclamo
    @PutMapping("/{idReclamo}/cambiar-estado")
    public ResponseEntity<String> cambiarEstado(@PathVariable int idReclamo, @RequestBody EstadoReclamo estado) {
            reclamoService.cambiarEstado(idReclamo, estado);
            return ResponseEntity.ok("Estado del reclamo actualizado.");
    }

    // Contar reclamos por estado
    @GetMapping("/estado/{idEstado}/contar")
    public ResponseEntity<Integer> contarReclamosPorEstado(@PathVariable int idEstado) {
        int cantidad = reclamoService.contarReclamosPorEstado(idEstado);
        return ResponseEntity.ok(cantidad);
    }

    // Obtener lista de reclamos por estado
    @GetMapping("/estado/{idEstado}")
    public ResponseEntity<List<Reclamo>> obtenerReclamosPorEstado(@PathVariable int idEstado) {
        List<Reclamo> reclamos = reclamoService.obtenerReclamosPorEstado(idEstado);
        return ResponseEntity.ok(reclamos);
    }
}
