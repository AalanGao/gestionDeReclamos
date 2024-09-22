package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Unidad;
import com.adriYalan.gestionDeReclamos.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UnidadDAO {

    private static UnidadDAO instancia;

    @Autowired
    private UnidadRepository unidadRepository;

    // Constructor privado para el patrón singleton
    private UnidadDAO() {}

    // Método para obtener la instancia del DAO
    public static UnidadDAO getInstancia() {
        if (instancia == null) {
            instancia = new UnidadDAO();
        }
        return instancia;
    }

    // Método para obtener todas las unidades
    public List<Unidad> getAllUnidades() {
        return unidadRepository.findAll();
    }

    // Método para obtener una unidad por su identificador
    public Optional<Unidad> getUnidadById(int identificador) {
        return unidadRepository.findById(identificador);
    }

    // Método para guardar una nueva unidad
    public Unidad guardarUnidad(Unidad unidad) {
        return unidadRepository.save(unidad);
    }

    // Método para actualizar una unidad
    public Unidad actualizarUnidad(Unidad unidad) {
        if (unidad.getIdentificador() > 0 && unidadRepository.existsById(unidad.getIdentificador())) {
            return unidadRepository.save(unidad);
        }
        return null;
    }

    // Método para eliminar una unidad por su identificador
    public void eliminarUnidad(int identificador) {
        unidadRepository.deleteById(identificador);
    }

    // Puedes agregar métodos personalizados si es necesario
}
