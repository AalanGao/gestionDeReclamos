package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Ubicacion;
import com.adriYalan.gestionDeReclamos.repository.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UbicacionDAO {

    private static UbicacionDAO instancia;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    private UbicacionDAO() {}

    public static UbicacionDAO getInstancia() {
        if (instancia == null) {
            instancia = new UbicacionDAO();
        }
        return instancia;
    }

    public void setRepository(UbicacionRepository repository) {this.ubicacionRepository = repository;}

    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public Optional<Ubicacion> getUbicacionById(int idUbicacion) {
        return ubicacionRepository.findById(idUbicacion);
    }

    public Ubicacion guardarUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public void eliminarUbicacion(int idUbicacion) {
        ubicacionRepository.deleteById(idUbicacion);
    }
}
