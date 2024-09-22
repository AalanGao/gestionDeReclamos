package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Inquilino;
import com.adriYalan.gestionDeReclamos.repository.InquilinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InquilinoDAO {

    private static InquilinoDAO instancia;

    @Autowired
    private InquilinoRepository inquilinoRepository;

    // Constructor privado para el patrón singleton
    private InquilinoDAO() {}

    // Método para obtener la instancia del DAO
    public static InquilinoDAO getInstancia() {
        if (instancia == null) {
            instancia = new InquilinoDAO();
        }
        return instancia;
    }

    // Método para obtener todos los inquilinos
    public List<Inquilino> getAllInquilinos() {
        return inquilinoRepository.findAll();
    }

    // Método para obtener un inquilino por ID
    public Optional<Inquilino> getInquilinoById(int id) {
        return inquilinoRepository.findById(id);
    }

    // Método para guardar un nuevo inquilino
    public Inquilino guardarInquilino(Inquilino inquilino) {
        return inquilinoRepository.save(inquilino);
    }

    // Método para eliminar un inquilino por ID
    public void eliminarInquilino(int id) {
        inquilinoRepository.deleteById(id);
    }
}
