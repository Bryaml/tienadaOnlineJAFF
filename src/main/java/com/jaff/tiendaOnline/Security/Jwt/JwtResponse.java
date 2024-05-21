package com.jaff.tiendaOnline.Security.Jwt;

import java.io.Serializable;

public class JwtResponse {
    private final String token;
    private final String message;
    private final String role;

    public JwtResponse(String token, String message, String role) {
        this.token = token;
        this.message = message;
        this.role = role;
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }
}
