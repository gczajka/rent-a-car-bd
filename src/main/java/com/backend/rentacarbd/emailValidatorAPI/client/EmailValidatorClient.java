package com.backend.rentacarbd.emailValidatorAPI.client;

import com.backend.rentacarbd.domain.VerificationDto;
import com.backend.rentacarbd.emailValidatorAPI.config.RapidApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class EmailValidatorClient {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RapidApiConfig rapidApiConfig;

    public VerificationDto validateEmail(String email) {
        URI url = UriComponentsBuilder.fromHttpUrl(rapidApiConfig.getValidatorEndpoint())
                .path(email)
                .build().encode().toUri();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("X-RapidAPI-Host", rapidApiConfig.getValidatorHost());
        headers.add("X-RapidAPI-Key", rapidApiConfig.getRapidApiKey());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<VerificationDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, VerificationDto.class);
        return response.getBody();
    }
}
