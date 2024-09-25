package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.TipoReclamo;
import com.adriYalan.gestionDeReclamos.repository.TipoReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TipoReclamoDAO {

    private static TipoReclamoDAO instancia;

    @Autowired
    private ReclamoRepository reclamoRepository;

    // Constructor privado para el patrón singleton
    private TipoReclamoDAO() {}

    // Método para obtener la instancia del DAO
    public static TipoReclamoDAO getInstancia() {
        if (instancia == null) {
            instancia = new TipoReclamoDAO();
        }
        return instancia;
    }

    @Autowired
    private TipoReclamoRepository tipoReclamoRepository;

    // Obtener todos los tipos de reclamo
    public List<TipoReclamo> getAllTiposReclamo() {
        return tipoReclamoRepository.findAll();
    }

    // Obtener un tipo de reclamo por ID
    public Optional<TipoReclamo> getTipoReclamoById(int idTipoReclamo) {
        return tipoReclamoRepository.findById(idTipoReclamo);
    }

    // Guardar o actualizar un tipo de reclamo
    public TipoReclamo guardarTipoReclamo(TipoReclamo tipoReclamo) {
        return tipoReclamoRepository.save(tipoReclamo);
    }

    // Eliminar un tipo de reclamo
    public void eliminarTipoReclamo(int idTipoReclamo) {
        tipoReclamoRepository.deleteById(idTipoReclamo);
    }
}
