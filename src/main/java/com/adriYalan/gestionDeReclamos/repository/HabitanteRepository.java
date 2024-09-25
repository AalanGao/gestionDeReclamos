package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Habitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface HabitanteRepository extends JpaRepository<Habitante, Integer> {

    Optional<Habitante> findByDocumento(String documento);

    @Modifying
    @Transactional
    @Query("DELETE FROM Habitante h WHERE h.identificador = :identificadorUnidad")
    void deleteByIdentificador(@Param("identificadorUnidad") int identificadorUnidad);
}
