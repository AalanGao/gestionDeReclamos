package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Ubicacion;
import com.adriYalan.gestionDeReclamos.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UbicacionDAO {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    // Obtener todas las ubicaciones
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    // Obtener una ubicación por ID
    public Optional<Ubicacion> getUbicacionById(int idUbicacion) {
        return ubicacionRepository.findById(idUbicacion);
    }

    // Guardar o actualizar una ubicación
    public Ubicacion guardarUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    // Eliminar una ubicación
    public void eliminarUbicacion(int idUbicacion) {
        ubicacionRepository.deleteById(idUbicacion);
    }
}
