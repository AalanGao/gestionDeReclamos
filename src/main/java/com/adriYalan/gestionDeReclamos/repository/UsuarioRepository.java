package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para buscar un usuario por su nombre de usuario


    // Método para buscar un usuario por su UID de Firebase
    //Usuario findByUid(String uid); // Asegúrate de que 'uid' sea un campo en tu entidad Usuario

    // Método para buscar un usuario por su correo electrónico
    Usuario findByEmail(String email); //
}
