package com.jaff.tiendaOnline.Controller.PaymentController;

public class PaymentResponse {

    private String paymentId;

    public PaymentResponse(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
