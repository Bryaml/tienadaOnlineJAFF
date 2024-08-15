
/*
package com.jaff.tiendaOnline.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PayPalPaymentService {

    @Autowired
    private PayPalAuthService payPalAuthService;

    public String processPayment(PayPalPaymentRequest payPalPaymentRequest) {
        String accessToken = payPalAuthService.getAccessToken();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");

        HttpEntity<PayPalPaymentRequest> request = new HttpEntity<>(payPalPaymentRequest, headers);
        ResponseEntity<PayPalPaymentResponse> response = restTemplate.exchange(
                "https://api.sandbox.paypal.com/v1/payments/payment",
                HttpMethod.POST,
                request,
                PayPalPaymentResponse.class
        );

        return response.getBody().getId();
    }
}

*/