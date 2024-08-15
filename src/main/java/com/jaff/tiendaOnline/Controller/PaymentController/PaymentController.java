package com.jaff.tiendaOnline.Controller.PaymentController;

import com.jaff.tiendaOnline.Service.PaymentService.PaymentRequest;
import com.jaff.tiendaOnline.Service.PaymentService.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private StripePaymentService stripePaymentService;

    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            String paymentIntentId = stripePaymentService.processPayment(paymentRequest);
            return ResponseEntity.ok(new PaymentResponse(paymentIntentId));
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PaymentResponse(e.getMessage()));
        }
    }
}
