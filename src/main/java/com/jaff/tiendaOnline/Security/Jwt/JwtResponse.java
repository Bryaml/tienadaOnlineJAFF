package com.jaff.tiendaOnline.Security.Jwt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaff.tiendaOnline.Controller.AuthController.CustomerDTO;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
    private String token;
    private String message;
    private CustomerDTO customer;

    public JwtResponse(String token, String message, CustomerDTO customer) {
        this.token = token;
        this.message = message;
        this.customer = customer;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
