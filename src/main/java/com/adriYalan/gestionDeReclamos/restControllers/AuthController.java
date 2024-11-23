package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.dto.DTOGenerator;
import com.adriYalan.gestionDeReclamos.dto.ReclamoDTO;
import com.adriYalan.gestionDeReclamos.dto.UsuarioDTO;
import com.adriYalan.gestionDeReclamos.dto.models.LoginRequest; // Importa la clase LoginRequest
import com.adriYalan.gestionDeReclamos.entity.Persona;
import com.adriYalan.gestionDeReclamos.entity.Reclamo;
import com.adriYalan.gestionDeReclamos.entity.Usuario; // Importa la entidad Usuario
import com.adriYalan.gestionDeReclamos.exception.EdificioException;
import com.adriYalan.gestionDeReclamos.exception.PersonaException;
import com.adriYalan.gestionDeReclamos.exception.UsuarioException;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private FirebaseAuth firebaseAuth;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PersonaService personaService;

    @GetMapping("/{documento}")
    public ResponseEntity<UsuarioDTO> getUsuarioByDocumento(@PathVariable String documento) throws UsuarioException {
        return ResponseEntity.ok(DTOGenerator.toUsuarioDTO(usuarioService.getUsuarioDocumento(documento)));
    }

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
    public ResponseEntity<Usuario> getAuthenticatedUser(@RequestHeader("Authorization") String token) throws PersonaException {
        try {
            // Eliminamos "Bearer " del token para obtener solo el token
            String firebaseToken = token.replace("Bearer ", "");
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(firebaseToken);
            String email = decodedToken.getEmail();

            // Buscamos el usuario en la base de datos usando el email
            Usuario usuario = usuarioService.getUsuarioPorEmail(email);
            if (usuario == null) {
                throw new PersonaException("El usuario no existe");
            }
            // Retornamos la información del usuario
            return ResponseEntity.ok(usuario);
        } catch (FirebaseAuthException e) {
            e.printStackTrace(); // Esto imprime la traza del error en la consola
            throw new PersonaException("El usuario no existe");
        } catch (Exception e) {
            e.printStackTrace(); // Captura otros errores no previstos
            throw new PersonaException("El usuario no existe");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String mail, @RequestParam String contraseña) {
        Usuario usuario = usuarioService.getUsuarioPorEmail(mail);
        if (usuario == null || !usuario.getPassword().equals(contraseña)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        return ResponseEntity.ok(Map.of("message", "Login exitoso", "role", usuario.getRol()));
    }

    @Transactional
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarUsuario(@RequestParam String mail) throws FirebaseAuthException {
        UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(mail);
        FirebaseAuth.getInstance().deleteUser(userRecord.getUid());
        usuarioService.deleteUsuario(mail);
        return ResponseEntity.ok("Usuario Eliminado correctamente.");
    }



}
