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

    private InquilinoDAO() {}

    public static InquilinoDAO getInstancia() {
        if (instancia == null) {
            instancia = new InquilinoDAO();
        }
        return instancia;
    }

    public List<Inquilino> getAllInquilinos() {
        return inquilinoRepository.findAll();
    }

    public Optional<Inquilino> getInquilinoById(int id) {
        return inquilinoRepository.findById(id);
    }

    public Optional<Inquilino> getInquilinoByDocumento(String documento) {
        return inquilinoRepository.findByDocumento(documento);
    }

    public Inquilino guardarInquilino(Inquilino inquilino) {
        return inquilinoRepository.save(inquilino);
    }

    public void eliminarInquilino(int id) {
        inquilinoRepository.deleteById(id);
    }

}
