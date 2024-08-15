package com.jaff.tiendaOnline.Controller.AddressController;

import com.jaff.tiendaOnline.Entity.Address;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
    private Long addressId;
    private String streetNumber;
    private String cp;
    private String state;
    private String city;
    private String village;

    public AddressDTO(Address address) {
        this.addressId = address.getAddressId();
        this.streetNumber = address.getStreetNumber();
        this.cp = address.getCp();
        this.state = address.getState();
        this.city = address.getCity();
        this.village = address.getVillage();
    }

    // Getters and setters
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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
