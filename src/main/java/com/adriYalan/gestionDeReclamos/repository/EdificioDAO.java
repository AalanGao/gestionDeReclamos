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

    private EdificioDAO() {}

    public static EdificioDAO getInstancia() {
        if (instancia == null) {
            instancia = new EdificioDAO();
        }
        return instancia;
    }

    public List<Edificio> getAllEdificios() {
        return edificioRepository.findAll();
    }

    public Optional<Edificio> getEdificioByCodigo(Long codigo) {
        return edificioRepository.findById(codigo);
    }

    public Edificio guardarEdificio(Edificio edificio) {
        return edificioRepository.save(edificio);
    }

    public Edificio actualizarEdificio(Edificio edificio) {
        if (edificio.getCodigo() != null && edificioRepository.existsById(edificio.getCodigo())) {
            return edificioRepository.save(edificio);
        }
        return null;
    }

    public void eliminarEdificio(Long codigo) {
        edificioRepository.deleteById(codigo);
    }

    // Método personalizado para buscar edificios por nombre y dirección
    public Optional<Edificio> getEdificioByNombreAndDireccion(String nombre, String direccion) {
        return Optional.ofNullable(edificioRepository.findByNombreAndDireccion(nombre, direccion));
    }
}
