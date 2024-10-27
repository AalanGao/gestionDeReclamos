package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDAO {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> findByUsername(String username) {
        return Optional.ofNullable(UsuarioRepository.findByUsername(username));
    }

    public Usuario saveUsuario(Usuario usuario) { // Manteniendo el nombre 'usuario'
        return usuarioRepository.save(usuario); // Cambiamos 'user' a 'usuario'
    }

    public Usuario findByUid(String uid) {
        return UsuarioRepository.findByUid(uid);
    }
}
