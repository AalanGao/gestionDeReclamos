package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Integer> {

    Optional<Unidad> findByEdificioCodigoAndPisoAndNumero(int codigo, String piso, String numero);

}
