package com.adriYalan.gestionDeReclamos.service;

import com.adriYalan.gestionDeReclamos.entity.Usuario;
import com.adriYalan.gestionDeReclamos.exception.UsuarioException;
import com.adriYalan.gestionDeReclamos.repository.UsuarioDAO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO; // Cambiamos a UsuarioDAO

    @Autowired
    private FirebaseAuth firebaseAuth;

    public Optional<String> getUserRole(String email) {
        Usuario user = usuarioDAO.findByEmail(email);
        return user != null ? Optional.of(user.getRol()) : Optional.empty();
    }

    public String login(String token) {
        try {
            FirebaseToken firebaseToken = firebaseAuth.verifyIdToken(token);
            String mail = firebaseToken.getEmail();
            return getUserRole(mail).orElse("Usuario sin rol asignado");
        } catch (FirebaseAuthException e) {
            return "Error de autenticación: " + e.getMessage();
        }
    }

    public Usuario getUsuarioPorEmail(String email)throws UsuarioException {
        Optional<Usuario> usuario =  usuarioDAO.findByEmailOptional(email);
        if(usuario.isPresent()){
            return usuario.get();
        } else {
            throw new UsuarioException("Usuario no encontrado");
        }
    }

    public Usuario getUsuarioDocumento(String documento) throws UsuarioException {
        if(usuarioDAO.findByDocumento(documento).isPresent()){
            return usuarioDAO.findByDocumento(documento).get();
        } else {
            throw new UsuarioException("Usuario no encontrado");
        }
    }

    public Usuario save(Usuario usuario) {
        return usuarioDAO.saveUsuario(usuario);
    }

    public void deleteUsuario(String email){
        usuarioDAO.deleteUsuario(email);
    }
}
