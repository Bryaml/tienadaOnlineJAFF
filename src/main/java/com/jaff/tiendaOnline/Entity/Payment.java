package com.jaff.tiendaOnline.Entity;

import jakarta.persistence.*;


import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Date date;
    private double amount;

    private String status;
    private String paymentMethod;
    private String clipPaymentId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String getClipPaymentId() {
        return clipPaymentId;
    }

    public void setClipPaymentId(String clipPaymentId) {
        this.clipPaymentId = clipPaymentId;
    }

}
