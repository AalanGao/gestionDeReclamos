package com.adriYalan.gestionDeReclamos.repository;

import com.adriYalan.gestionDeReclamos.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDAO {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> findByEmailOptional(String email) {
        return Optional.ofNullable(usuarioRepository.findByEmail(email));
    }

    public Optional<Usuario> findByDocumento(String documento) {
        return usuarioRepository.findByDocumento(documento);
    }

    public void deleteUsuario(String email) {
        usuarioRepository.deleteByEmail(email);
    }
}
