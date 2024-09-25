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
    private TipoReclamoRepository tipoReclamoRepository;

    private TipoReclamoDAO() {}

    public static TipoReclamoDAO getInstancia() {
        if (instancia == null) {
            instancia = new TipoReclamoDAO();
        }
        return instancia;
    }

    public List<TipoReclamo> getAllTiposReclamo() {
        return tipoReclamoRepository.findAll();
    }

    public Optional<TipoReclamo> getTipoReclamoById(int idTipoReclamo) {
        return tipoReclamoRepository.findById(idTipoReclamo);
    }

    public TipoReclamo guardarTipoReclamo(TipoReclamo tipoReclamo) {
        return tipoReclamoRepository.save(tipoReclamo);
    }

    public void eliminarTipoReclamo(int idTipoReclamo) {
        tipoReclamoRepository.deleteById(idTipoReclamo);
    }
}
