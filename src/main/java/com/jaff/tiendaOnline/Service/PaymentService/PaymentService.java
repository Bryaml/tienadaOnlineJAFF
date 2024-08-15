/*
package com.jaff.tiendaOnline.Service.PaymentService;

import com.jaff.tiendaOnline.Entity.Orders;
import com.jaff.tiendaOnline.Entity.Payment;
import com.jaff.tiendaOnline.Repository.OrderRepository;
import com.jaff.tiendaOnline.Repository.PaymentRepository;
import com.jaff.tiendaOnline.Service.clip.ClipPaymentRequest;
import com.jaff.tiendaOnline.Service.clip.ClipPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {

    @Autowired
    private ClipPaymentService clipPaymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Payment processPayment(PaymentRequest paymentRequest) {
        Orders order = orderRepository.findById(paymentRequest.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Llama a Clip para procesar el pago
        String clipPaymentId = clipPaymentService.processClipPayment(createClipPaymentRequest(paymentRequest));

        // Guarda el pago en la base de datos
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setDate(new Date());
        payment.setStatus("COMPLETED");
        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setOrder(order);
        payment.setClipPaymentId(clipPaymentId); // Guarda el ID del pago de Clip

        return paymentRepository.save(payment);
    }

    private ClipPaymentRequest createClipPaymentRequest(PaymentRequest paymentRequest) {
        // Mapea los campos de PaymentRequest a ClipPaymentRequest
        ClipPaymentRequest clipRequest = new ClipPaymentRequest();
        // Configura los campos necesarios
        return clipRequest;
    }
}
*/