package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.dto.models.LoginRequest; // Importa la clase LoginRequest
import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Usuario; // Importa la entidad Usuario
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.repository.UsuarioDAO;
import com.adriYalan.gestionDeReclamos.service.PersonaService;
import com.adriYalan.gestionDeReclamos.service.UsuarioService; // Importa el servicio UsuarioService
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
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
    private UsuarioService usuarioService;
    @Autowired
    private PersonaService personaService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String documento,@RequestParam String rol) throws PersonaException {
        Persona persona = personaService.getPersonaByDocumento(documento);
        Usuario user = new Usuario();
        user.setEmail(documento+"@alanAdri.com");
        user.setPassword((persona.getNombre()+documento).replaceAll(" ", "").replaceAll(",", ""));
        user.setRol(rol);
        user.setDocumento(documento);

        try {
            // 1. Crear el usuario en Firebase con email y password
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            // 2. Crear el objeto Usuario y guardarlo en la base de datos
            Usuario usuario = new Usuario();
            usuario.setEmail(user.getEmail());
            usuario.setPassword(user.getPassword());  // Considera almacenar una versión encriptada
            usuario.setDocumento(user.getDocumento());
            usuario.setRol(user.getRol());

            // Guardar el usuario en la base de datos
            usuarioService.save(usuario);

            // Retornar un mensaje de éxito
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario registrado exitosamente con email: " + user.getEmail());

        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error en la autenticación de Firebase: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }


    @GetMapping("/me") // Método GET para obtener la información del usuario autenticado
    public ResponseEntity<?> getAuthenticatedUser(@RequestHeader("Authorization") String token) {
        try {
            // Eliminamos "Bearer " del token para obtener solo el token
            String firebaseToken = token.replace("Bearer ", "");
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(firebaseToken);
            String email = decodedToken.getEmail();

            // Buscamos el usuario en la base de datos usando el email
            Usuario usuario = usuarioService.getUsuarioPorEmail(email);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado: " + email);
            }

            // Retornamos la información del usuario
            return ResponseEntity.ok("Usuario autenticado: " + email + " con rol: " + usuario.getRol());
        } catch (FirebaseAuthException e) {
            e.printStackTrace(); // Esto imprime la traza del error en la consola
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error de autenticación: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Captura otros errores no previstos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }

    }
}
