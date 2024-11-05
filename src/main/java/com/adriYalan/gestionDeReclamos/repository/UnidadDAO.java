package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Edificio;
import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.exception.UnidadException;
import com.adriYalan.gestionDeReclamos.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UnidadDAO {

    private static UnidadDAO instancia;

    @Autowired
    private UnidadRepository unidadRepository;
    @Autowired
    private PersonaRepository personaRepository;
    private UnidadDAO() {}

    public static UnidadDAO getInstancia() {
        if (instancia == null) {
            instancia = new UnidadDAO();
        }
        return instancia;
    }

    public List<Unidad> getAllUnidades() {
        return unidadRepository.findAll();
    }

    public Optional<Unidad> getUnidadById(int identificador) {
        return unidadRepository.findById(identificador);
    }

    public Unidad guardarUnidad(Unidad unidad) {
        return unidadRepository.save(unidad);
    }

    public Unidad actualizarUnidad(Unidad unidad) {
        if (unidadRepository.existsById(unidad.getIdentificador())) {
            return unidadRepository.save(unidad);
        }
        return null;
    }

    public void eliminarUnidad(int identificador) {
        unidadRepository.deleteById(identificador);
    }

    public Optional<Unidad> getUnidadByDetalles(int codigoEdificio, String piso, String numero) {
        return unidadRepository.findByEdificioCodigoAndPisoAndNumero(codigoEdificio, piso, numero);
    }



}
