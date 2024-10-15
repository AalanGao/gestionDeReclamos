package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReclamoDAO {

    private static ReclamoDAO instancia;

    @Autowired
    private ReclamoRepository reclamoRepository;

    private ReclamoDAO() {}

    public static ReclamoDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReclamoDAO();
        }
        return instancia;
    }

    public List<Reclamo> getAllReclamos() {
        return reclamoRepository.findAll();
    }

    public Optional<Reclamo> getReclamoById(int idReclamo) {
        return reclamoRepository.findById(idReclamo);
    }

    public Reclamo guardarReclamo(Reclamo reclamo) {
        return reclamoRepository.save(reclamo);
    }

    public int contarReclamosPorEstado(int idEstado){
       return reclamoRepository.contarReclamosPorEstado(idEstado);
    }

    public List<Reclamo> ReclamosPorEstado(int idEstado){
        return instancia.ReclamosPorEstado(idEstado);
    }

    public void eliminarReclamo(int idReclamo) {
        reclamoRepository.deleteById(idReclamo);
    }
}