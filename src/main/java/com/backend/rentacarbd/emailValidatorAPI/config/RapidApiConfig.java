package com.backend.rentacarbd.emailValidatorAPI.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RapidApiConfig {
    @Value("${rapidapi.api.key}")
    private String rapidApiKey;

    @Value("${validator.api.host}")
    private String validatorHost;

    @Value("${validator.api.endpoint}")
    private String validatorEndpoint;
}
