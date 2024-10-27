package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.dto.models.LoginRequest; // Importa la clase LoginRequest
import com.adriYalan.gestionDeReclamos.entity.Usuario; // Importa la entidad Usuario
import com.adriYalan.gestionDeReclamos.service.UsuarioService; // Importa el servicio UsuarioService
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Autowired
    private UsuarioService usuarioService; // Inyectamos el servicio para manejar usuarios

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticación con Firebase
            FirebaseToken token = firebaseAuth.verifyIdToken(loginRequest.getToken());
            String email = token.getEmail();

            // Buscamos el usuario en la base de datos usando el email
            Usuario usuario = usuarioService.getUsuarioPorEmail(email);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado: " + email);
            }

            // Retornamos un mensaje de éxito junto con el rol del usuario
            return ResponseEntity.ok("Usuario autenticado: " + email + " con rol: " + usuario.getRol());

        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error de autenticación: " + e.getMessage());
        }
    }
}
