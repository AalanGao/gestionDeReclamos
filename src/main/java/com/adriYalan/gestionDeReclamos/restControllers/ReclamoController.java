package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.dto.DTOGenerator;
import com.adriYalan.gestionDeReclamos.dto.ReclamoDTO;
import com.adriYalan.gestionDeReclamos.entity.Imagen;
import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.entity.EstadoReclamo;
import com.adriYalan.gestionDeReclamos.exception.EdificioException;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.exception.ReclamoException;
import com.adriYalan.gestionDeReclamos.repository.ImagenRepository;
import com.adriYalan.gestionDeReclamos.service.FirebaseStorageService;
import com.adriYalan.gestionDeReclamos.service.PersonaService;
import com.adriYalan.gestionDeReclamos.service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamos")
public class ReclamoController {

    @Autowired
    private ReclamoService reclamoService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @Autowired
    private ImagenRepository imagenRepository;

    @GetMapping()
    public ResponseEntity<List<ReclamoDTO>> obtenerReclamos() {
        List<Reclamo> reclamos = reclamoService.allReclamos();
        return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(reclamos));
    }

    // Obtener reclamos por código de edificio
    @GetMapping("/edificio/{codigo}")
    public ResponseEntity<List<ReclamoDTO>> obtenerReclamosPorEdificio(@PathVariable int codigo) {
        List<Reclamo> reclamos = reclamoService.reclamosPorEdificio(codigo);
        return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(reclamos));
    }

    // Obtener reclamos por unidad
    @GetMapping("/unidad/{id}")
    public ResponseEntity<List<ReclamoDTO>> obtenerReclamosPorUnidad(@PathVariable int id) {
        List<Reclamo> reclamos = reclamoService.reclamosPorUnidad(id);
        return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(reclamos));
    }

    // Obtener reclamo por número de reclamo
    @GetMapping("/{id}")
    public ResponseEntity<ReclamoDTO> obtenerReclamoPorNumero(@PathVariable int id) {
        Reclamo reclamo = reclamoService.getReclamoById(id);
        return ResponseEntity.ok(DTOGenerator.toReclamoDTO(reclamo));
    }

    // Obtener reclamos por documento de persona
    @GetMapping("/persona/{documento}")
    public ResponseEntity<List<ReclamoDTO>> obtenerReclamosPorPersona(@PathVariable String documento) {
        List<Reclamo> reclamos = reclamoService.reclamosPorPersona(documento);
        return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(reclamos));
    }

    // Agregar un nuevo reclamo
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarReclamo(
            @RequestParam int codigo,
            @RequestParam String piso,
            @RequestParam String numero,
            @RequestParam String documento,
            @RequestParam String descripcion,
            @RequestParam int idTipTrec,
            @RequestParam(required = false) List<MultipartFile> imagenes
    ) throws PersonaException, EdificioException, IOException {
        int idReclamo = reclamoService.agregarReclamo(codigo, piso, numero, documento, descripcion, idTipTrec, null);
        this.saveImg(imagenes, idReclamo);
        return ResponseEntity.ok("Reclamo agregado con ID: " + idReclamo);
    }

    @PostMapping("/agregarZonaComun")
    public ResponseEntity<String> agregarReclamo(
            @RequestParam int codigo,
            @RequestParam String documento,
            @RequestParam int idUbicacion,
            @RequestParam String descripcion,
            @RequestParam int idTipTrec,
            @RequestParam(required = false) List<MultipartFile> imagenes
    ) throws PersonaException, EdificioException, IOException {
        int idReclamo = reclamoService.agregarReclamo(codigo, documento, idUbicacion, descripcion, idTipTrec, null);
        this.saveImg(imagenes, idReclamo);
        return ResponseEntity.ok("Reclamo agregado con ID: " + idReclamo);
    }

    // Cambiar estado de un reclamo
    @PutMapping("/cambiarEstado")
    public ResponseEntity<ReclamoDTO> cambiarEstado(@RequestParam int idReclamo, @RequestParam int idEstado) {
            Reclamo reclamo = reclamoService.cambiarEstado(idReclamo, idEstado);
            return ResponseEntity.ok(DTOGenerator.toReclamoDTO(reclamo));
    }

    // Contar reclamos por estado
    @GetMapping("/estado/{idEstado}/contar")
    public ResponseEntity<Integer> contarReclamosPorEstado(@PathVariable int idEstado) {
        int cantidad = reclamoService.contarReclamosPorEstado(idEstado);
        return ResponseEntity.ok(cantidad);
    }

    // Obtener lista de reclamos por estado
    @GetMapping("/estado/{idEstado}")
    public ResponseEntity<List<ReclamoDTO>> obtenerReclamosPorEstado(@PathVariable int idEstado) {
        List<Reclamo> reclamos = reclamoService.obtenerReclamosPorEstado(idEstado);
        return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(reclamos));
    }

    // Obtener lista de reclamos por tipo
    @GetMapping("/tipo/{idTipoReclamo}")
    public ResponseEntity<List<ReclamoDTO>> obtenerReclamosPorIdTipoReclamo(@PathVariable int idTipoReclamo) {
        List<Reclamo> reclamos = reclamoService.obtenerReclamosPorTipo(idTipoReclamo);
        return ResponseEntity.ok(DTOGenerator.toReclamoDTOList(reclamos));
    }






    private void saveImg(List<MultipartFile> imagenes, int idReclamo) throws IOException {
        if (imagenes != null && !imagenes.isEmpty()) {
            List<Imagen> listaImagenes = new ArrayList<>();
            for (MultipartFile imagen : imagenes) {
                String urlImagen = firebaseStorageService.uploadFile(imagen, idReclamo); // Subir la imagen y obtener la URL
                Imagen nuevaImagen = new Imagen();
                nuevaImagen.setPath(urlImagen);
                nuevaImagen.setTipo(imagen.getContentType()); // Tipo de la imagen
                nuevaImagen.setIdReclamo(idReclamo);
                listaImagenes.add(nuevaImagen);
            }
            imagenRepository.saveAll(listaImagenes);
        }
    }


}
