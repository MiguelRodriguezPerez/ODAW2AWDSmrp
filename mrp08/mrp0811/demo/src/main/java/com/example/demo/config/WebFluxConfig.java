package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebFluxConfig {
    Logger logger = LoggerFactory.getLogger(WebFluxConfig.class);
    @Bean
    public WebClient getWebClient() {
        return WebClient.builder()
        .baseUrl("https://api.frankfurter.app/")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
    }
}
