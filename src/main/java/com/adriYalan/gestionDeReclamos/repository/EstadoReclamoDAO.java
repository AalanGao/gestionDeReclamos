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

    public List<EstadoReclamo> getAllEstadosReclamo() {
        return estadoReclamoRepository.findAll();
    }

    public Optional<EstadoReclamo> getEstadoReclamoById(int idEstado) {
        return estadoReclamoRepository.findById(idEstado);
    }

    public EstadoReclamo guardarEstadoReclamo(EstadoReclamo estadoReclamo) {
        return estadoReclamoRepository.save(estadoReclamo);
    }

    public void eliminarEstadoReclamo(int idEstado) {
        estadoReclamoRepository.deleteById(idEstado);
    }
}
