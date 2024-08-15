/*

package com.jaff.tiendaOnline.Service.clip;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;

@Service
public class ClipAuthService {

    @Value("${clip.api.client-id}")
    private String clientId;

    @Value("${clip.api.client-secret}")
    private String clientSecret;

    @Value("${clip.api.token-url}")
    private String tokenUrl;

    public String getAuthToken() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        String authValue = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
        headers.add("Authorization", "Basic " + authValue);
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);
        ResponseEntity<ClipTokenResponse> response = restTemplate.exchange(tokenUrl, HttpMethod.POST, request, ClipTokenResponse.class);

        return response.getBody().getAccessToken();
    }
}
 */