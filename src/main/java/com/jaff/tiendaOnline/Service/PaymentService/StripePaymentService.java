package com.jaff.tiendaOnline.Service.PaymentService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

@Service
public class StripePaymentService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public String processPayment(PaymentRequest paymentRequest) throws StripeException {
        // Crear los parámetros para el PaymentIntent de Stripe
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(paymentRequest.getAmount())
                        .setCurrency("mxn")
                        .addPaymentMethodType("card") // Configurar el tipo de método de pago
                        .setDescription("Payment for order ID: " + paymentRequest.getOrderId())
                        .setPaymentMethod(paymentRequest.getPaymentMethod()) // Asociar el método de pago
                        .build();

        // Crear el PaymentIntent en Stripe
        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // Confirmar el PaymentIntent
        PaymentIntentConfirmParams confirmParams =
                PaymentIntentConfirmParams.builder()
                        .setPaymentMethod(paymentRequest.getPaymentMethod()) // Confirmar con el método de pago
                        .build();

        paymentIntent = paymentIntent.confirm(confirmParams);

        // Devolver el ID del PaymentIntent como resultado
        return paymentIntent.getId();
    }
}
