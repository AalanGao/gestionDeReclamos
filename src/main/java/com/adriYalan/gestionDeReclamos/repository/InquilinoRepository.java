package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Inquilino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, Integer> {

    Optional<Inquilino> findByDocumento(String documento);

}
