package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    static Usuario findByUsername(String username) {
        return null;
    }

    static Usuario findByUid(String uid) // Asumiendo que 'uid' es el identificador que usarás de Firebase
    {
        return null;
    }
}
