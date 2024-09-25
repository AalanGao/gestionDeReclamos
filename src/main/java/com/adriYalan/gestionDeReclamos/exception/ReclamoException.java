package com.adriYalan.gestionDeReclamos.exception;

public class ReclamoException extends RuntimeException {
    public ReclamoException(String message) {
        super(message);
    }

    public ReclamoException(String message, Throwable cause) {
        super(message, cause);
    }
}
