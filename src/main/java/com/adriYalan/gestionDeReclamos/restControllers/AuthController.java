package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.models.LoginRequest; // Importa la clase LoginRequest
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private FirebaseAuth firebaseAuth;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            // Autenticación con Firebase
            FirebaseToken token = firebaseAuth.verifyIdToken(loginRequest.getToken());
            return "Usuario autenticado: " + token.getEmail();
        } catch (FirebaseAuthException e) {
            return "Error de autenticación: " + e.getMessage();
        }
    }
}
