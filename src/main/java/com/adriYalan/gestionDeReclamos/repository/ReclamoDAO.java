package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReclamoDAO {

    @Autowired
    private ReclamoRepository reclamoRepository;

    // Obtener todos los reclamos
    public List<Reclamo> getAllReclamos() {
        return reclamoRepository.findAll();
    }

    // Obtener un reclamo por ID
    public Optional<Reclamo> getReclamoById(int idReclamo) {
        return reclamoRepository.findById(idReclamo);
    }

    // Guardar o actualizar un reclamo
    public Reclamo guardarReclamo(Reclamo reclamo) {
        return reclamoRepository.save(reclamo);
    }

    // Eliminar un reclamo
    public void eliminarReclamo(int idReclamo) {
        reclamoRepository.deleteById(idReclamo);
    }
}
