package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.EstadoReclamo;
import com.adriYalan.gestionDeReclamos.repository.EstadoReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EstadoReclamoDAO {

    @Autowired
    private EstadoReclamoRepository estadoReclamoRepository;

    // Obtener todos los estados de reclamo
    public List<EstadoReclamo> getAllEstadosReclamo() {
        return estadoReclamoRepository.findAll();
    }

    // Obtener un estado de reclamo por ID
    public Optional<EstadoReclamo> getEstadoReclamoById(int idEstado) {
        return estadoReclamoRepository.findById(idEstado);
    }

    // Guardar o actualizar un estado de reclamo
    public EstadoReclamo guardarEstadoReclamo(EstadoReclamo estadoReclamo) {
        return estadoReclamoRepository.save(estadoReclamo);
    }

    // Eliminar un estado de reclamo
    public void eliminarEstadoReclamo(int idEstado) {
        estadoReclamoRepository.deleteById(idEstado);
    }
}
