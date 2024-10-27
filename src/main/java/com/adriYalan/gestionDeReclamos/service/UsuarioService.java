package com.adriYalan.gestionDeReclamos.service;

import com.adriYalan.gestionDeReclamos.entity.Usuario;
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
    private UsuarioDAO UsuarioDao; // Cambiamos de UserRepository a UserDao

    @Autowired
    private FirebaseAuth firebaseAuth;

    public Optional<String> getUserRole(String uid) {
        // Busca el usuario en la base de datos usando el UID de Firebase
        Usuario user = UsuarioDao.findByUid(uid);
        return user != null ? Optional.of(user.getRol()) : Optional.empty();
    }

    public String login(String token) {
        try {
            FirebaseToken firebaseToken = firebaseAuth.verifyIdToken(token);
            String uid = firebaseToken.getUid();
            // Obtiene el rol del usuario
            return getUserRole(uid).orElse("Usuario sin rol asignado");
        } catch (FirebaseAuthException e) {
            return "Error de autenticación: " + e.getMessage();
        }
    }
}
