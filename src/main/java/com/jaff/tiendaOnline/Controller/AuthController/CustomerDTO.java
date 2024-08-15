package com.jaff.tiendaOnline.Controller.AuthController;

import com.jaff.tiendaOnline.Controller.AddressController.AddressDTO;
import com.jaff.tiendaOnline.Entity.Address;
import com.jaff.tiendaOnline.Entity.Customer;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private Long customerId;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String role;

    // Campos de dirección
    private String streetNumber;
    private String cp;
    private String state;
    private String city;
    private String village;

    // Constructor
    public CustomerDTO(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.name = customer.getName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.phone = customer.getPhone();
        this.role = customer.getRole();

        // Agregar campos de dirección
        if (!customer.getAddresses().isEmpty()) {
            Address address = customer.getAddresses().get(0);
            this.streetNumber = address.getStreetNumber();
            this.cp = address.getCp();
            this.state = address.getState();
            this.city = address.getCity();
            this.village = address.getVillage();
        }
    }
    // Getters y setters
    // ...

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }
}
