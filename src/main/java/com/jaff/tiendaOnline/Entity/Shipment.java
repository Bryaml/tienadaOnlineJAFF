package com.jaff.tiendaOnline.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipmentId;
    private Date date;
    private Date addressDate;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;
    // getters and setters

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getAddressDate() {
        return addressDate;
    }

    public void setAddressDate(Date addressDate) {
        this.addressDate = addressDate;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders order) {
        this.orders = order;
    }
}