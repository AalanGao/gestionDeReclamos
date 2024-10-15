package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
    @Query("SELECT COUNT(r) FROM Reclamo r WHERE r.estadoReclamo.id = :idEstado")
    int contarReclamosPorEstado(@Param("idEstado") int idEstado);

    @Query("SELECT r FROM Reclamo r WHERE r.estadoReclamo.id = :idEstado")
    List<Reclamo> findReclamosByEstado(@Param("idEstado") int idEstado);
}
