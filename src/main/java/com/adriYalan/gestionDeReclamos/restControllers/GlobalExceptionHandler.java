package com.adriYalan.gestionDeReclamos.restControllers;

import com.adriYalan.gestionDeReclamos.exception.EdificioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de la excepción EdificioException y devolvemos un 404 si el edificio no es encontrado
    @ExceptionHandler(EdificioException.class)
    public ResponseEntity<String> handleEdificioException(EdificioException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);  // 404 Not Found
    }

    /*// Manejo de errores de validación (ejemplo de 400 Bad Request)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);  // 400 Bad Request
    }*/

    // Manejo de excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // 500 Internal Server Error
    }
}

