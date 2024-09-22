package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long> {
    // Puedes agregar métodos personalizados aquí si es necesario
    Edificio findByNombreAndDireccion(String nombre, String direccion);
}
