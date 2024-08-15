/*
package com.jaff.tiendaOnline.Service.clip;

import com.jaff.tiendaOnline.Service.clip.ClipAuthService;
import com.jaff.tiendaOnline.Service.clip.ClipPaymentRequest;
import com.jaff.tiendaOnline.Service.clip.ClipPaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;


@Service
public class ClipPaymentService {

    @Autowired
    private ClipAuthService clipAuthService;

    public String processClipPayment(ClipPaymentRequest paymentRequest) {
        String accessToken = clipAuthService.getAuthToken();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");

        HttpEntity<ClipPaymentRequest> request = new HttpEntity<>(paymentRequest, headers);
        ResponseEntity<ClipPaymentResponse> response = restTemplate.exchange("https://api.clip.mx/v1/payments", HttpMethod.POST, request, ClipPaymentResponse.class);

        return response.getBody().getId(); // Devuelve el ID del pago o el resultado de Clip
    }
}

*/