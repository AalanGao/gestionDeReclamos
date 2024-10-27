package com.adriYalan.gestionDeReclamos.dto.models;

public class LoginRequest {
    private String token;

    // Constructor vacío
    public LoginRequest() {}

    // Getter y Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
