package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Habitante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HabitanteDAO {

    private static HabitanteDAO instancia;

    @Autowired
    private HabitanteRepository habitanteRepository;

    private HabitanteDAO() {}

    public static HabitanteDAO getInstancia() {
        if (instancia == null) {
            instancia = new HabitanteDAO();
        }
        return instancia;
    }

    public List<Habitante> getAllHabitantes() {
        return habitanteRepository.findAll();
    }

    public Optional<Habitante> getHabitanteById(int id) {
        return habitanteRepository.findById(id);
    }

    public Optional<Habitante> getHabitanteByDocumento(String documento) {
        return habitanteRepository.findByDocumento(documento);
    }

    public Habitante guardarHabitante(Habitante habitante) {
        return habitanteRepository.save(habitante);
    }

    public void eliminarHabitantesPorUnidad(int identificadorUnidad) {
        habitanteRepository.deleteHabitanteByIdentificador(identificadorUnidad);
    }

    public void eliminarInquilinoPorUnidad(int identificadorUnidad) {
        habitanteRepository.deleteInquilinoByIdentificador(identificadorUnidad);
    }

}
