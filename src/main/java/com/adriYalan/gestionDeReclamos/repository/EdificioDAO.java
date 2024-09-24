package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Edificio;
import com.adriYalan.gestionDeReclamos.repository.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EdificioDAO {

    private static EdificioDAO instancia;

    @Autowired
    private EdificioRepository edificioRepository;

    // Constructor privado para el patrón singleton
    private EdificioDAO() {}

    // Método para obtener la instancia del DAO
    public static EdificioDAO getInstancia() {
        if (instancia == null) {
            instancia = new EdificioDAO();
        }
        return instancia;
    }

    public void setRepository(EdificioRepository repository) {this.edificioRepository = repository;}

    // Método para obtener todos los edificios
    public List<Edificio> getAllEdificios() {
        return edificioRepository.findAll();
    }

    // Método para obtener un edificio por su código
    public Optional<Edificio> getEdificioByCodigo(Long codigo) {
        return edificioRepository.findById(codigo);
    }

    // Método para guardar un edificio
    public Edificio guardarEdificio(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    // Método para actualizar un edificio
    public Edificio actualizarEdificio(Edificio edificio) {
        if (edificio.getCodigo() != null && edificioRepository.existsById(edificio.getCodigo())) {
            return edificioRepository.save(edificio);
        }
        return null;
    }

    // Método para eliminar un edificio por su código
    public void eliminarEdificio(Long codigo) {
        edificioRepository.deleteById(codigo);
    }

    // Método personalizado para buscar edificios por nombre y dirección
    public Optional<Edificio> getEdificioByNombreAndDireccion(String nombre, String direccion) {
        return Optional.ofNullable(edificioRepository.findByNombreAndDireccion(nombre, direccion));
    }
}
