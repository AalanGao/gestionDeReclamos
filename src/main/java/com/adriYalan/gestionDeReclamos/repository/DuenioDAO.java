package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Duenio;
import com.adriYalan.gestionDeReclamos.repository.DuenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DuenioDAO {

    private static DuenioDAO instancia;

    @Autowired
    private DuenioRepository duenioRepository;

    private DuenioDAO() {}

    public static DuenioDAO getInstancia() {
        if (instancia == null) {
            instancia = new DuenioDAO();
        }
        return instancia;
    }

    public List<Duenio> getAllDuenios() {
        return duenioRepository.findAll();
    }

    public Optional<Duenio> getDuenioById(int id) {
        return duenioRepository.findById(id);
    }

    public Duenio guardarDuenio(Duenio duenio) {
        return duenioRepository.save(duenio);
    }

    public void eliminarDuenio(int id) {
        duenioRepository.deleteById(id);
    }
}
