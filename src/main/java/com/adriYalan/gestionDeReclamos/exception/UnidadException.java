package com.adriYalan.gestionDeReclamos.exception;

public class UnidadException extends RuntimeException {
    public UnidadException(String message) {
        super(message);
    }
    public UnidadException(String message, Throwable cause) {
        super(message, cause);
    }
}
